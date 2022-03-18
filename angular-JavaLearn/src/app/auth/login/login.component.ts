import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../shared/auth.service';
import { LoginRequest } from './login-request';
import { Notify } from 'notiflix/build/notiflix-notify-aio';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup
  loginRequest: LoginRequest

  constructor(private authService: AuthService) {
    this.loginForm = new FormGroup ({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.loginRequest = {
      username: '',
      password: ''
    };
  }

  ngOnInit(): void {}

  login(){
    this.loginRequest.username = this.loginForm.get('username')?.value
    this.loginRequest.password = this.loginForm.get('password')?.value

    this.authService.login(this.loginRequest).subscribe({
      error: (e) => {
        console.error(e)
        Notify.warning('Something went wrong')},
      complete: () => {
        console.info('complete')
        Notify.success('You are successfully logged in.')} 
    })
  }

  ngDestroy(): void {}

}
