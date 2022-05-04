import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-page-maker',
  templateUrl: './page-maker.component.html',
  styleUrls: ['./page-maker.component.css']
})
export class PageMakerComponent implements OnInit {

  pageTypeControl = new FormControl();
  pageType?: string;

  constructor() { }

  ngOnInit(): void {
  }
}
