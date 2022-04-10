import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/shared/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn: boolean = false
  isAdmin: boolean = false
  username: string

  constructor(private authService: AuthService) {
    this.username = this.authService.getUserName()
  }

  ngOnInit(): void {
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data)
    this.authService.admin.subscribe((data: boolean) => this.isAdmin = data)
    this.authService.username.subscribe((data: string) => this.username = data)
  }

  logout(): void {
    this.authService.logout()
  }

}
