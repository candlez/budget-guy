import { Component, Signal } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { MatTableModule } from '@angular/material/table';
import { Category } from '../../model/domain/Category';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { toSignal } from '@angular/core/rxjs-interop';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { DeleteCategoriesComponent } from './delete-categories/delete-categories.component';
import { BehaviorSubject, switchMap } from 'rxjs';

@Component({
  selector: 'app-categories-page',
  imports: [CommonModule, MatTableModule, MatProgressSpinnerModule, MatButtonModule, RouterLink, MatIcon],
  templateUrl: './categories-page.component.html',
  styleUrl: './categories-page.component.css',
})
export class CategoriesPageComponent {
  columns: string[] = ["name", "description", "createdAt", "optionsButton"];
  private readonly refresher: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);
  categories: Signal<Category[]>;

  constructor(private categoryService: CategoryService, private dialog: MatDialog) {
    this.categories = toSignal(
      this.refresher.pipe(switchMap(() => this.categoryService.getCategories())),
      { initialValue: [] }
    );
  }

  openDialog(category: Category): void {
    const dialogRef = this.dialog.open(
      DeleteCategoriesComponent,
      { data: category }
    );

    dialogRef.afterClosed().subscribe((result: boolean): void => {
      this.refresher.next();
    });
  }
}
