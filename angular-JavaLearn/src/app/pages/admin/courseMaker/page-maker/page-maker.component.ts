import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course/Course';
import { Page } from 'src/app/models/course/Page';
import { PageService } from 'src/app/services/page.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-page-maker',
  templateUrl: './page-maker.component.html',
  styleUrls: ['./page-maker.component.css']
})
export class PageMakerComponent implements OnInit {

  course: Course

  pageList: Array<Page> = []

  pageTypeControl = new FormControl()
  pageType!: string

  constructor(private pageService: PageService, private utils: UtilsService, private router: Router) {
    this.course = Object.assign({} as Course, this.router.getCurrentNavigation()?.extras.state)
  }

  ngOnInit(): void {
    this.pageService.getAllPages(this.course.id).subscribe( data => {
      this.pageList = data
    })
  }

  delete(id: bigint) {
    if(confirm("Are you sure to delete this page?")) {
      this.pageService.deletePage(id)
      this.utils.reloadComponent(this.router.url)
    }
  }
}
