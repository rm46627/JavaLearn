import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Course } from 'src/app/models/course/Course';
import { CourseService } from 'src/app/services/course.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
   selector: 'app-make-course-modal',
   templateUrl: './make-course-modal.component.html',
   styleUrls: ['./make-course-modal.component.css']
})
export class MakeCourseModalComponent implements OnInit {

  createModalForm: FormGroup
  course: Course

  modalRef!: BsModalRef;
  constructor(private modalService: BsModalService, private courseService: CourseService, private utilsService: UtilsService) {
    this.createModalForm = new FormGroup ({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.course = {
      id: BigInt(0),
      title: '',
      description: ''
    };
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  create(){
    this.course.title = this.createModalForm.get('title')?.value
    this.course.description = this.createModalForm.get('description')?.value

    this.courseService.saveCourse(this.course)
    this.modalRef.hide()
  }

  // handling tab button in text areas as "\t"
  handleKeydown(event:any) {
    this.utilsService.handleKeydown(event)
  }

  ngOnInit(): void {}
}