import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Page } from '../models/course/Page';

@Injectable({
  providedIn: 'root'
})
export class PageService {

  private pageData = new BehaviorSubject<Page>({} as Page);
  currentPageData = this.pageData.asObservable();
  
  constructor() {}

  updatePageData(pd: Page) {
    this.pageData.next(pd)
  }

}
