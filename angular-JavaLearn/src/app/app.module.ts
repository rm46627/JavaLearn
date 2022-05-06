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
import { DeleteAccountModalComponent } from './components/delete-account-modal/delete-account-modal.component';
import { CourseMakerComponent } from './pages/admin/courseMaker/courseMaker.component';
import { AuthInterceptorProviders } from './interceptors/auth.interceptor';
import {PreviewModalComponent} from './components/preview-modal/preview-modal.component';
import { PageMakerComponent } from './pages/admin/courseMaker/page-maker/page-maker.component';
import { CodingPageComponent } from './pages/admin/courseMaker/page-maker/coding-page/coding-page.component';
import { QuizPageComponent } from './pages/admin/courseMaker/page-maker/quiz-page/quiz-page.component';
import { TextPageComponent } from './pages/admin/courseMaker/page-maker/text-page/text-page.component';
import { MakeCourseModalComponent } from './components/make-course-modal/make-course-modal.component';
import { PageManagerComponent } from './pages/admin/courseMaker/page-manager/page-manager.component';

import {MatInputModule} from '@angular/material/input';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';

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
    DeleteAccountModalComponent,
    CourseMakerComponent,
    MakeCourseModalComponent,
    PageMakerComponent,
    PreviewModalComponent,
    TextPageComponent,
    QuizPageComponent,
    CodingPageComponent,
    PageManagerComponent
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
    MatButtonToggleModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule
  ],
  providers: [BsDropdownConfig, BsModalService, AuthInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
