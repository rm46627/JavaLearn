import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { RequestBaseService } from './request-base.service';

const API_URL = environment.WEBSITE_URL + '/admin'

@Injectable({
  providedIn: 'root'
})
export class AdminService extends RequestBaseService{

  constructor(authService: AuthService, httpClient: HttpClient) { 
    super(authService, httpClient)
  }

  findAllUsers(): Observable<any> {
    return this.httpClient.get(API_URL + '/users', {headers: this.getHeaders})
  }
}
