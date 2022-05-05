import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notify } from 'notiflix';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course/Course';
import { AuthService } from './auth.service';
import { RequestBaseService } from './request-base.service';

const API_URL = environment.WEBSITE_URL + '/courses'
const ADMIN_API_URL = environment.WEBSITE_URL + '/admin/courses'
@Injectable({
  providedIn: 'root'
})
export class CourseService extends RequestBaseService{

  constructor(authService: AuthService, httpClient: HttpClient) { 
    super(authService, httpClient)
  }

  findAllCourses(): Observable<any> {
    return this.httpClient.get(API_URL + '/all', {headers: this.getHeaders})
  }

  saveCourse(course: Course) {
    return this.httpClient.post(ADMIN_API_URL + '/save', course, {headers: this.getHeaders, responseType: 'text'})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('You are successfully created course.') 
        }
    });
  }

  deleteCourse(courseId: bigint) {
    return this.httpClient.delete(ADMIN_API_URL + '/remove/' + courseId, {headers: this.getHeaders})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('You are successfully deleted course.') 
        }
    });
  }
}
