import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Page } from 'src/app/models/course/Page';
import { PageService } from 'src/app/services/page-maker.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-coding-page',
  templateUrl: './coding-page.component.html',
  styleUrls: ['./coding-page.component.css']
})
export class CodingPageComponent implements OnInit {

  pageData : Page

  constructor(private fb: FormBuilder, private pageService: PageService, private utils: UtilsService) {
    this.pageData = {} as Page
  }

  ngOnInit(): void {
  }

  codeForm = this.fb.group({
    pageTitle: ['', Validators.required],
    codeGroups: this.fb.array([])
  });
  
  get codeFields() {
    return this.codeForm.get('codeGroups') as FormArray;
  }
  
  addCodeGroup() {
    this.codeFields.push(this.fb.group({
      type: ['', Validators.required],
      text: ['', Validators.required]
    }));
  }

  deleteCodeGroup(index: number) {
    this.codeFields.removeAt(index);
  }

  saveFormsAsPageData(){
    this.pageData.title = this.codeForm.get(['pageTitle'])?.value
    for(let group of this.codeFields.controls){
      group.get(['text'])
    }
  }

  savePage(){
    this.saveFormsAsPageData()
    this.pageService.updatePageData(this.pageData)
  }

    // handling tab button in text areas as "\t"
    handleKeydown(event:any) {
      this.utils.handleKeydown(event)
    }

}
