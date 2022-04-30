import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { BsDropdownModule,BsDropdownConfig } from 'ngx-bootstrap/dropdown';
import { ModalModule, BsModalService } from 'ngx-bootstrap/modal';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { AdminMenuComponent } from './pages/admin/adminMenu/adminMenu.component';
import { UsersComponent } from './pages/admin/users/users.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { SignupComponent } from './pages/auth/signup/signup.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ModalComponent } from './components/modal/modal.component';
import { CourseMakerComponent } from './pages/admin/courseMaker/courseMaker.component';
import { AuthInterceptorProviders } from './interceptors/auth.interceptor';

import {MatSliderModule} from '@angular/material/slider'
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    AdminMenuComponent,
    UsersComponent,
    ProfileComponent,
    ModalComponent,
    CourseMakerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    BrowserAnimationsModule,
    BsDropdownModule.forRoot(),
    ModalModule,
    MatSliderModule
  ],
  providers: [BsDropdownConfig, BsModalService, AuthInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
