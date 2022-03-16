import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignupRequest } from '../signup/signup-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  signup(signupRequest: SignupRequest){
    return this.httpClient.post("http://localhost:8080/api/auth/signup", signupRequest, {responseType: 'text'});
  }
}

