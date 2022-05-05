import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course/Course';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-courseMaker',
  templateUrl: './courseMaker.component.html',
  styleUrls: ['./courseMaker.component.css']
})
export class CourseMakerComponent implements OnInit {

  courseList: Array<Course> = []
  
  constructor(private courseService: CourseService) {}  
  
  ngOnInit(): void {
    this.courseService.findAllCourses().subscribe( data => {
      this.courseList = data
    })
  }

  delete(id: bigint) {
    this.courseService.deleteCourse(id)
  }

}
