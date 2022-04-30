import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { catchError, Observable, switchMap, throwError } from 'rxjs';
import jwtDecode, { JwtPayload } from 'jwt-decode';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user';

const HEADER_AUTHORIZATION = "authorization"

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  private jwt: JwtPayload = {}

  constructor(private authService: AuthService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(request.headers.has(HEADER_AUTHORIZATION)) {
      return this.handleToken(request, next)
    } else {
      return next.handle(request);
    }
  }

  private handleToken(request: HttpRequest<unknown>, next: HttpHandler){
    this.jwt = jwtDecode(this.authService.getCurrentUserValue.accessToken)
    
    const nowInSecs = Date.now() / 1000
    const exp = this.jwt.exp || 0

    if(exp > nowInSecs){
      return next.handle(request)
    } else {
      return this.authService.refreshToken().pipe(
        switchMap((response: User) => {
          this.authService.setUser(response)
          const cloned = request.clone({
            headers: request.headers.set(HEADER_AUTHORIZATION, 'Bearer ' + response.accessToken)
          })
          return next.handle(cloned)
        }),
        catchError(err => {
          this.authService.logout()
          this.router.navigate(['/login'])
          return throwError(() => new Error(err))
        })
      )
    }
  }
}

export const AuthInterceptorProviders = [
  { provide : HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
]
