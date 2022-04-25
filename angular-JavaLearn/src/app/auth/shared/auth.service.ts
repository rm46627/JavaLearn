import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginRequest } from '../login/login-request';
import { LoginResponse } from '../login/login-response';
import { SignupRequest } from '../signup/signup-request';
import { map } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/admin/users/user';
import { Role } from './role.enum';
import { Router } from '@angular/router';

const API_URL = environment.WEBSITE_URL + '/api/auth'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  public currentUser: Observable<User>
  private currentUserSubject: BehaviorSubject<User>
  
  constructor(
    private router: Router,
    private httpClient: HttpClient) { 

      let storageUser
      const storageUserAsStr = localStorage.getItem('currentUser')
      if(storageUserAsStr) {
        storageUser = JSON.parse(storageUserAsStr)
      }

      this.currentUserSubject = new BehaviorSubject<User>(storageUser)
      this.currentUser = this.currentUserSubject.asObservable()
    }
  
  public get getCurrentUserValue() : User {
    return this.currentUserSubject.value
  }

  signup(signupRequest: SignupRequest){
    return this.httpClient.post(API_URL + "/signup", signupRequest, {responseType: 'text'})
  }

  login(logReq: LoginRequest) : Observable<any>{
    return this.httpClient.post<User>(API_URL + "/login", logReq).pipe(
      map(response => {
        if(response){
          this.setUser(response)
        }
        return response
      })
    )
  }

  setUser(user: User){
    localStorage.setItem('currentUser', JSON.stringify(user))
    this.currentUserSubject.next(user)
  }

  logout() {
    localStorage.removeItem('currentUser')
    let emptyUser = {} as User;
    this.currentUserSubject.next(emptyUser)
    this.router.navigate(['/home'])
  }

  refreshToken(): Observable<any> {
    return this.httpClient.post(API_URL + "/refresh-token?token=" + this.getCurrentUserValue?.refreshToken, {})
  }
    
  // isLoggedIn(): boolean {
  //   return this.currentUserSubject.value.id != 
  // }

  isAdmin(){
    return this.currentUserSubject.value.role === Role.ADMIN
  }

}