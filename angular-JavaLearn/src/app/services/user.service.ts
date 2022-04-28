import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/login-request';
import { AuthService } from './auth.service';
import { RequestBaseService } from './request-base.service';

const API_URL = environment.WEBSITE_URL + '/user'

@Injectable({
  providedIn: 'root'
})
export class UserService extends RequestBaseService {

  constructor(authService: AuthService, httpClient: HttpClient) { 
    super(authService, httpClient)
  }

  deleteUser(logReq: LoginRequest): Observable<any> {
    return this.httpClient.delete(API_URL + '/removeuser', {headers: this.getHeaders, body: logReq})
  }
}
