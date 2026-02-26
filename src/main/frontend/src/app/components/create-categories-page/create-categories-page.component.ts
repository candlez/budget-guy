import { Component } from '@angular/core';
import { MatButton } from "@angular/material/button";
import { RouterLink } from "@angular/router";
import { MatFormField, MatInput, MatLabel } from '@angular/material/input';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryRequestDto } from '../../model/dto/CategoryRequest.dto';

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

  constructor(private formBuilder: FormBuilder, private categoryService: CategoryService) {
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
    this.categoryService.createCategory(dto).subscribe(res => console.log(res));
  }
}
