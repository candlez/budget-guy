import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CategoriesPageComponent } from './components/categories-page/categories-page.component';

export const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "categories", component: CategoriesPageComponent},
  {path: "**", component: NotFoundComponent}
];
