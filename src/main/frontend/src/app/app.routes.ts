import { Routes } from '@angular/router';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  {path: "error", component: ErrorComponent},
  {path: "login", component: LoginComponent}
];
