import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginRequest } from '../login/login-request';
import { LoginResponse } from '../login/login-response';
import { SignupRequest } from '../signup/signup-request';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient,
    private localStorage: LocalStorageService) { }

  signup(signupRequest: SignupRequest){
    return this.httpClient.post("http://localhost:8080/api/auth/signup", signupRequest, {responseType: 'text'});
  }

  login(loginRequestPayload: LoginRequest): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/auth/login',
      loginRequestPayload).pipe(map(data => {
        this.localStorage.store('authenticationToken', data.authenticationToken);
        this.localStorage.store('username', data.username);

        return true;
      }));
  }
}
