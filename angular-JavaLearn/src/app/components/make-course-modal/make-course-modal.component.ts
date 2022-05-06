import { Component, Input, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { CourseRequest } from 'src/app/models/course/CourseRequest';
import { CourseService } from 'src/app/services/course.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
   selector: 'app-make-course-modal',
   templateUrl: './make-course-modal.component.html',
   styleUrls: ['./make-course-modal.component.css']
})
export class MakeCourseModalComponent implements OnInit {

  createModalForm: FormGroup
  courseRequest: CourseRequest

  @Input()
  updateCourse: bigint = BigInt(0)
  @Input()
  title = ""
  @Input()
  description = ""

  modalRef!: BsModalRef;
  constructor(private modalService: BsModalService, private courseService: CourseService, private utilsService: UtilsService) {
    this.createModalForm = new FormGroup ({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.courseRequest = {
      title: '',
      description: ''
    };
  }
  ngOnInit(): void {
    this.createModalForm.controls['title'].setValue(this.title);
    this.createModalForm.get('description')?.setValue(this.description);
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  create(){
    this.courseRequest.title = this.createModalForm.get('title')?.value
    this.courseRequest.description = this.createModalForm.get('description')?.value
    if(this.updateCourse){
      this.courseService.updateCourse(this.courseRequest, this.updateCourse)
    } else {
      this.courseService.saveCourse(this.courseRequest)
    }
    this.modalRef.hide()
    this.utilsService.reloadComponent('/admin/maker')
  }

  // handling tab button in text areas as "\t"
  handleKeydown(event:any) {
    this.utilsService.handleKeydown(event)
  }

}