import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Notify } from 'notiflix/build/notiflix-notify-aio';
import { DeleteAccountModalComponent } from 'src/app/components/delete-account-modal/delete-account-modal.component';
import { Role } from 'src/app/models/role.enum';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user: User = {} as User

  constructor(
    private authService: AuthService, 
    private userService: UserService, 
    private adminService: AdminService, 
    private router: Router) {
    this.user = Object.assign({} as User, this.router.getCurrentNavigation()?.extras.state)
  }
  
  @ViewChild('content') content: any;
  public ngOnInit() {}

  public open() {
    if (!true) {
      // Dont open the modal
    } else {
      // Open the modal
      this.content.open();
    }
  }

  isAdmin() : boolean {
    return this.authService.isAdmin()
  }

  delete(){
    this.adminService.deleteUser(this.user.id)
    this.router.navigate(["admin/users"])
  }

}
