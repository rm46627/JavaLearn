import { Component, Input, OnInit, SimpleChanges, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Page } from 'src/app/models/page/Page';
import { CourseMakerService } from 'src/app/services/course-maker.service';

@Component({
  selector: 'app-preview-modal',
  templateUrl: './preview-modal.component.html',
  styleUrls: ['./preview-modal.component.css']
})
export class PreviewModalComponent implements OnInit {

  pageData: Page

  modalRef!: BsModalRef;
  constructor(private modalService: BsModalService, private courseMakerService: CourseMakerService) {
    this.pageData = {} as Page
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-xl'})
  }

  ngOnInit(): void {
    this.courseMakerService.currentPageData.subscribe(pg => this.pageData = pg);
  }

}
