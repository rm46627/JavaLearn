import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Page } from '../models/page/Page';

@Injectable({
  providedIn: 'root'
})
export class CourseMakerService {

  private pageData = new BehaviorSubject<Page>({} as Page);
  currentPageData = this.pageData.asObservable();
  
  constructor() {}

  updatePageData(pd: Page) {
    this.pageData.next(pd)
  }

}
