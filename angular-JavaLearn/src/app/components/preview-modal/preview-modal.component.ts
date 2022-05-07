import { Component, Input, OnInit, SimpleChanges, TemplateRef } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Page } from 'src/app/models/course/Page';
import { PageService } from 'src/app/services/page.service';

@Component({
  selector: 'app-preview-modal',
  templateUrl: './preview-modal.component.html',
  styleUrls: ['./preview-modal.component.css']
})
export class PreviewModalComponent implements OnInit {

  pageData!: Page
  modalRef!: BsModalRef
  
  constructor(private modalService: BsModalService, private pageService: PageService) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {class: 'modal-xl'})
  }

  ngOnInit(): void {
    this.pageService.currentPageData.subscribe(pg => this.pageData = pg);
  }

}
