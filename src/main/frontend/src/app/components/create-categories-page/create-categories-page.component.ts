import { Component } from '@angular/core';
import { MatButton } from "@angular/material/button";
import { Router, RouterLink } from "@angular/router";
import { MatFormField, MatInput, MatLabel } from '@angular/material/input';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryRequestDto } from '../../model/dto/CategoryRequest.dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Category } from '../../model/domain/Category';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-categories-page',
  imports: [
    MatButton,
    RouterLink,
    MatInput,
    MatLabel,
    MatFormField,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-categories-page.component.html',
  styleUrl: './create-categories-page.component.css',
})
export class CreateCategoriesPageComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private categoryService: CategoryService,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      name: [''],
      description: ['']
    });
  }

  submitForm(): void {
    const dto: CategoryRequestDto = {
      name: this.form.value.name,
      description: this.form.value.description
    }
    this.categoryService.createCategory(dto).subscribe({
      next: (value: Category): void => {
        this.successSnackBar();
        this.router.navigate(['/categories']);
      },
      error: (err: HttpErrorResponse): void => {
        console.log(err)
        this.errorSnackBar(err.error.error.message);
      }
    });
  }

  successSnackBar(): void {
    this.snackBar.open("Created Category", "dismiss",  { duration: 1500, horizontalPosition: "end" });
  }

  errorSnackBar(message: string): void {
    this.snackBar.open(message, "dismiss",  { horizontalPosition: "end", panelClass: ['error-snackbar'] });
  }
}
