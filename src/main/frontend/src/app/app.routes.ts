import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CategoriesPageComponent } from './components/categories-page/categories-page.component';
import { MainComponent } from './components/main/main.component';
import { CreateCategoriesPageComponent } from './components/create-categories-page/create-categories-page.component';

export const routes: Routes = [
  {path: "login", component: LoginComponent},
  {
    path: "",
    component: MainComponent,
    children: [
      {path: "categories/create", component: CreateCategoriesPageComponent},
      {path: "categories", component: CategoriesPageComponent},
      {path: "", redirectTo: "categories", pathMatch: "full"}
    ]
  },
  {path: "**", component: NotFoundComponent}
];
