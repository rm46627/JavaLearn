import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notify } from 'notiflix';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/login-request';
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

  deleteUser(userId: bigint) {
    return this.httpClient.delete(API_URL + '/removeuser/' + userId, {headers: this.getHeaders})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('User deleted.')
        }
    });
  }
}
