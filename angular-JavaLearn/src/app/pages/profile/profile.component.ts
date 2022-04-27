import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user: User = {} as User

  constructor(private router: Router) {
    this.user = Object.assign({} as User, this.router.getCurrentNavigation()?.extras.state)
  }

}
