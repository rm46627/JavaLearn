import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser: User

  constructor(private authService: AuthService, private router: Router) {
    this.currentUser = {} as User;
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data
    });
  }

  ngOnInit(): void {}

  profile(){
    this.router.navigate(['/profile'], {state:this.authService.getCurrentUserValue})
  }

  isAdmin() : boolean {
    return this.authService.isAdmin()
  }

  logout(): void {
    this.authService.logout()
  }

  // isLoggedIn(): boolean {
  //   return this.authService.isLoggedIn()
  // }

}
