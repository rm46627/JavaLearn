import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { LoginRequest } from '../../../models/login-request';
import { Notify } from 'notiflix/build/notiflix-notify-aio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup
  loginRequest: LoginRequest

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup ({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.loginRequest = {
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {
    if(this.authService.getCurrentUserValue?.id){
      // this.router.navigate([])
    }
  }

  login(){
    this.loginRequest.username = this.loginForm.get('username')?.value
    this.loginRequest.password = this.loginForm.get('password')?.value

    this.authService.login(this.loginRequest).subscribe({
      error: (e) => {
        console.error(e)
        Notify.warning('Something went wrong')},
      complete: () => {
        Notify.success('You are successfully logged in.') 
        // this.router.navigate(['/'])}
      }
    })
  }

  ngDestroy(): void {}

}
