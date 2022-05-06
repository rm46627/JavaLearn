import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course/Course';
import { CourseService } from 'src/app/services/course.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-courseMaker',
  templateUrl: './courseMaker.component.html',
  styleUrls: ['./courseMaker.component.css']
})
export class CourseMakerComponent implements OnInit {

  courseList: Array<Course> = []
  
  constructor(private courseService: CourseService, private router: Router, private utils: UtilsService) {}  
  
  ngOnInit(): void {
    this.courseService.findAllCourses().subscribe( data => {
      this.courseList = data
    })
  }

  delete(id: bigint) {
    if(confirm("Are you sure to delete this course?")) {
      this.courseService.deleteCourse(id)
      this.utils.reloadComponent(this.router.url)
    }
  }

}
