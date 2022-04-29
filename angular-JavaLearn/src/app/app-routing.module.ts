import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { AdminMenuComponent } from './pages/admin/adminMenu/adminMenu.component';
import { UsersComponent } from './pages/admin/users/users.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { SignupComponent } from './pages/auth/signup/signup.component';
import { HomeComponent } from './pages/home/home.component';
import { Role } from './models/role.enum';
import { ProfileComponent } from './pages/profile/profile.component';

const routes: Routes = [
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},

  { path: 'admin', 
    component: AdminMenuComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ADMIN]}
  },

  { path: 'admin/users', 
    component: UsersComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ADMIN]}
  },

  { path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard],
    data: {roles: [Role.ADMIN, Role.USER]}
  },

  {path: '**', redirectTo: 'home' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
