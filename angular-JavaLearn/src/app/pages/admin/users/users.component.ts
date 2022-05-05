import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { AdminService } from 'src/app/services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  userList: Array<User> = []

  constructor(private adminService: AdminService, private router:Router) {}

  ngOnInit(): void { 
    this.adminService.findAllUsers().subscribe( data => {
        this.userList = data
    })
  }

  detail(user: User){
    // this.router.navigate(['/profile', user.id], {state:user})
    this.router.navigate(['/profile'], {state:user})
  }
}
