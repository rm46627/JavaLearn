import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Page } from 'src/app/models/course/Page';
import { PageRequest } from 'src/app/models/course/page-request';
import { PageService } from 'src/app/services/page.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-text-page',
  templateUrl: './text-page.component.html',
  styleUrls: ['./text-page.component.css']
})
export class TextPageComponent implements OnInit {

  @Input()
  courseId!: bigint

  @Input()
  pageData!: Page

  pageRequest!: PageRequest

  textForm = this.fb.group({
    pageName: ['', Validators.required],
    text: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private pageService: PageService, private utils: UtilsService) {}

  ngOnInit(): void {
    if(Page != null){
      this.textForm.controls['pageName'].setValue(this.pageData.name)
      this.textForm.get('text')?.setValue(this.pageData.data)
    }
  }

  saveFormsAsPageData(){
    this.pageData.name = this.textForm.get(['pageName'])?.value
    this.pageData.data = this.textForm.get(['text'])?.value
    this.pageData.courseId = this.courseId
    this.pageData.type = "TEXT"
  }

  saveForPreview(){
    this.saveFormsAsPageData()
    this.pageService.updatePageData(this.pageData)
  }

  createPage(){
    this.pageRequest = {
      order: this.pageData.pageOrder,
      name: this.pageData.name,
      data: this.pageData.data,
      type: this.pageData.type
    };
    this.pageService.createPage(this.pageRequest, this.pageData.courseId)
    this.utils.reloadComponent('/admin/maker/page')
  }
  
  // handling tab button in text areas as "\t"
  handleKeydown(event:any) {
    this.utils.handleKeydown(event)
  }

}
