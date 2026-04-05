import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose, MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';
import { Category } from '../../../model/domain/Category';
import { MatButton } from '@angular/material/button';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-delete-categories',
  imports: [
    MatDialogTitle,
    MatDialogActions,
    MatButton,
    MatDialogContent
  ],
  templateUrl: './delete-categories.component.html',
  styleUrl: './delete-categories.component.css',
})
export class DeleteCategoriesComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public readonly data: Category,
    @Inject(MatDialogRef<Category>) private dialogRef: MatDialogRef<Category>,
    private categoryService: CategoryService
  ) {
  }

  close(): void {
    this.dialogRef.close(false);
  }

  submitDelete(): void {
    this.categoryService.deleteCategory(this.data.categoryId).subscribe({
      next: (): void => {
        this.dialogRef.close(true);
      },
      error: (error: Error): void => {
        console.log(error);
      }
    })
  }
}
