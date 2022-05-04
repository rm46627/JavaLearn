import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { TextPage } from 'src/app/models/page/TextPage';
import { CourseMakerService } from 'src/app/services/course-maker.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-text-page',
  templateUrl: './text-page.component.html',
  styleUrls: ['./text-page.component.css']
})
export class TextPageComponent implements OnInit {

  pageData: TextPage

  textForm = this.fb.group({
    pageTitle: ['', Validators.required],
    text: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private courseMakerService: CourseMakerService, private utils: UtilsService) {
    this.pageData = new TextPage()
  }

  ngOnInit(): void {
  }

  saveFormsAsPageData(){
    this.pageData.title = this.textForm.get(['pageTitle'])?.value
    this.pageData.body = this.textForm.get(['text'])?.value
  }

  savePage(){
    this.saveFormsAsPageData()
    this.courseMakerService.updatePageData(this.pageData)
  }
  
  // handling tab button in text areas as "\t"
  handleKeydown(event:any) {
    this.utils.handleKeydown(event)
  }

}
