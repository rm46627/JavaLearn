import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Notify } from 'notiflix';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../models/course/Page';
import { PageRequest } from '../models/course/page-request';
import { AuthService } from './auth.service';
import { RequestBaseService } from './request-base.service';

const API_URL = environment.WEBSITE_URL + '/pages/'
const ADMIN_API_URL = environment.WEBSITE_URL + '/admin/pages/'
@Injectable({
  providedIn: 'root'
})
export class PageService extends RequestBaseService{

  constructor(authService: AuthService, httpClient: HttpClient) { 
    super(authService, httpClient)
  }

  /// Manage data for preview-modal

  private pageData = new BehaviorSubject<Page>(new Page());
  currentPageData = this.pageData.asObservable();

  updatePageData(pd: Page ) {
   this.pageData.next(pd)
  }

  /// HTTP
  list: Array<Page> = []

  getAllPages(courseId: bigint): Observable<any> {
    return this.httpClient.get(API_URL + courseId, {headers: this.getHeaders})
  }

  createPage(pageRequest: PageRequest, courseId: bigint) {
    return this.httpClient.post(ADMIN_API_URL + 'save/' + courseId , pageRequest, {headers: this.getHeaders, responseType: 'text'})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('You are successfully created page.') 
        }
    });
  }

  updatePage(page: Page, courseId : bigint) {
    return this.httpClient.put(ADMIN_API_URL + 'update/' + courseId, page, {headers: this.getHeaders, responseType: 'text'})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('You are successfully updated page.') 
        }
    });
  }

  deletePage(pageId: bigint) {
    return this.httpClient.delete(ADMIN_API_URL + 'remove/' + pageId, {headers: this.getHeaders})
    .subscribe({
        error: error => {
            console.error('There was an error!', error);
        },
        complete: () => {
          Notify.success('You are successfully deleted page.') 
        }
    });
  }

}
