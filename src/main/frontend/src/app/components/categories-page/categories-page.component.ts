import { Component, Signal } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { MatTableModule } from '@angular/material/table';
import { Category } from '../../model/domain/Category';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { toSignal } from '@angular/core/rxjs-interop';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-categories-page',
  imports: [CommonModule, MatTableModule, MatProgressSpinnerModule, MatButtonModule, RouterLink],
  templateUrl: './categories-page.component.html',
  styleUrl: './categories-page.component.css',
})
export class CategoriesPageComponent {
  columns: string[] = ["name", "description", "createdAt"];
  categories: Signal<Category[]>;

  constructor(private categoryService: CategoryService) {
    this.categories = toSignal(
      this.categoryService.getCategories(),
      { initialValue: [] }
    );
  }
}
