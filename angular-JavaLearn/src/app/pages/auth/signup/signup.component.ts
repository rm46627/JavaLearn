import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { SignupRequest } from '../../../models/signup-request';
import { Notify } from 'notiflix/build/notiflix-notify-aio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html'
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  signupRequest: SignupRequest;

  constructor(private authService : AuthService,
    private router: Router) {
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

  ngOnInit(): void {
    if(this.authService.getCurrentUserValue?.id){
      this.router.navigate(['/home'])
    }
  }

  signup(){
    this.signupRequest.username = this.signupForm.get('username')?.value;
    this.signupRequest.email = this.signupForm.get('email')?.value;
    this.signupRequest.password = this.signupForm.get('password')?.value;

    this.authService.signup(this.signupRequest).subscribe({
      error: (e) => {
        if(e.status = 409){
          Notify.warning('Username already exist.')
        } else {
        console.error(e);
        Notify.warning('Something went wrong')
        }},
      complete: () => {
        console.info('complete') 
        Notify.success('Registration successful. Check your email for account activation link')
        this.router.navigate(['/login'])} 
    })
  }

}
