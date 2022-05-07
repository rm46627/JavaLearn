import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Page } from 'src/app/models/course/Page';
import { PageService } from 'src/app/services/page.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-quiz-page',
  templateUrl: './quiz-page.component.html',
  styleUrls: ['./quiz-page.component.css']
})
export class QuizPageComponent implements OnInit {

  pageData : Page

  quizForm: any

  constructor(private fb: FormBuilder, private pageService: PageService, private utils: UtilsService) {
    this.pageData = {} as Page
  }

  ngOnInit(): void {
    this.quizForm = this.fb.group({
      quizDetails: this.fb.group({
        quizTitle: ['', Validators.required],
        description: ['', Validators.required],
      }),
      questions: this.fb.array([])
    })
  }

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

  saveFormsAsPageData(){
    this.pageData.name = this.quizForm.get(['pageName'])?.value
  }

  savePage(){
    this.saveFormsAsPageData()
    this.pageService.updatePageData(this.pageData)
  }

  handleKeydown(event:any) {
    this.utils.handleKeydown(event)
  }

}
