import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { Notify } from 'notiflix/build/notiflix-notify-aio';
import { LoginRequest } from 'src/app/models/login-request';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
   selector: 'app-modal',
   templateUrl: './modal.component.html',
   styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  loginForm: FormGroup
  loginRequest: LoginRequest

  modalRef!: BsModalRef;
  constructor(private modalService: BsModalService, private userService: UserService, private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup ({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.loginRequest = {
      username: '',
      password: ''
    };
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  verify(){
    this.loginRequest.username = this.loginForm.get('username')?.value
    this.loginRequest.password = this.loginForm.get('password')?.value

    this.userService.deleteUser(this.loginRequest).subscribe({
      error: (e) => {
        console.error(e)
        Notify.warning('Something went wrong')},
      complete: () => {
        Notify.success('You are successfully deleted your account.') 
        // this.router.navigate(['/'])}
      }
    })
    this.modalRef.hide()
    this.authService.logout()
    this.router.navigate(["home"])
  }

   ngOnInit(): void {}
}