import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Page } from 'src/app/models/page/Page';

import { CourseMakerService } from 'src/app/services/course-maker.service';

@Component({
  selector: 'app-courseMaker',
  templateUrl: './courseMaker.component.html',
  styleUrls: ['./courseMaker.component.css']
})
export class CourseMakerComponent implements OnInit {

  
  constructor(private fb: FormBuilder, private courseMakerService: CourseMakerService) {}  
  
  ngOnInit(): void {}

}
