import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../shared/auth.service';
import { SignupRequest } from './signup-request';
import { Notify } from 'notiflix/build/notiflix-notify-aio';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  signupRequest: SignupRequest;

  constructor(private authService : AuthService) {
    this.signupForm = new FormGroup ({
      username: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)])
    });

    this.signupRequest = {
      username: '',
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {}

  signup(){
    this.signupRequest.username = this.signupForm.get('username')?.value;
    this.signupRequest.email = this.signupForm.get('email')?.value;
    this.signupRequest.password = this.signupForm.get('password')?.value;

    this.authService.signup(this.signupRequest).subscribe({
      error: (e) => {
        console.error(e);
        Notify.warning('Something went wrong')},
      complete: () => {
        console.info('complete'); 
        Notify.success('Registration successful. Check your email for account activation link')} 
    })
  }

}
