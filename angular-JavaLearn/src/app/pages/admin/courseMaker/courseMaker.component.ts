import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-courseMaker',
  templateUrl: './courseMaker.component.html',
  styleUrls: ['./courseMaker.component.css']
})
export class CourseMakerComponent implements OnInit {
  
  quizForm: any

  constructor(private fb: FormBuilder) {}  

  ngOnInit(): void {
    this.quizForm = this.fb.group({
      quizDetails: this.fb.group({
        quizTitle: ['', Validators.required],
        description: ['', Validators.required],
      }),
      questions: this.fb.array([])
    })
  }

  //
  // Coding challange
  //

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

  //
  // Quiz
  //
  
  get questions(): FormGroup {
    return this.fb.group({
      question: ['', Validators.required],
      answers: this.fb.array([]),
    })
  }

  get answers(): FormGroup {
    return this.fb.group({
      answer: ['', Validators.required],
      isItCorrect: ['', Validators.required],
    })
  }

  addQuestion() {
    (this.quizForm.get('questions') as FormArray).push(this.questions)
  }

  deleteQuestion(index : number) {
    (this.quizForm.get('questions') as FormArray).removeAt(index)
  }

  addAnswer(question: { get: (arg0: string) => FormGroup[]; }) {
    question.get('answers').push(this.answers)
  }

  deleteAnswer(question: { get: (arg0: string) => { (): any; new(): any; removeAt: { (arg0: number): void; new(): any; }; }; }, index : number) {
    question.get('answers').removeAt(index)
  }

  savePage() {
  }
}
