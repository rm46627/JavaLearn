import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
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

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();
  
  constructor(private httpClient: HttpClient,
    private localStorage: LocalStorageService) { }
    
  signup(signupRequest: SignupRequest){
    return this.httpClient.post("http://localhost:8080/api/auth/signup", signupRequest, {responseType: 'text'})
  }
  
  login(loginRequestPayload: LoginRequest): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/auth/login',
      loginRequestPayload).pipe(map(data => {
        this.localStorage.store('authenticationToken', data.authenticationToken)
        this.localStorage.store('username', data.username)

        this.loggedIn.emit(true);
        this.username.emit(data.username);
        return true;
      }));
  }

  logout() {
    this.localStorage.clear('authenticationToken')
    this.localStorage.clear('username')

    this.loggedIn.emit(false);
    this.username.emit('');
  }

  getAuthenticationToken(): string {
    return this.localStorage.retrieve('authenticationToken')
  }
    
  isLoggedIn(): boolean {
    return this.getAuthenticationToken() != null
  }
  
  getUserName() {
    return this.localStorage.retrieve('username')
  }

}