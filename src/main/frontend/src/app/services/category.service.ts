import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Category } from '../model/domain/Category';
import { CategoryResponseDto } from '../model/dto/CategoryResponse.dto';
import { ApiResponseDto } from '../model/dto/rest/ApiResponse.dto';
import { ListItemDto } from '../model/dto/rest/ListItem.dto';
import { SingleItemDto } from '../model/dto/rest/SingleItem.dto';
import { mapSingleItem } from '../model/mapper/SingleItem.mapper';
import { CategoryRequestDto } from '../model/dto/CategoryRequest.dto';
import { DeletedItemDto } from '../model/dto/rest/DeletedItem.dto';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  public getCategories(): Observable<Category[]> {
    return this.http.get<ApiResponseDto<ListItemDto<CategoryResponseDto>>>(
      "api/category",
      {withCredentials: true}
    ).pipe(map(
      res => res.data.items.map(dto => Category.fromDto(dto)))
    );
  }

  public createCategory(dto: CategoryRequestDto): Observable<Category> {
    return this.http.post<ApiResponseDto<SingleItemDto<CategoryResponseDto>>>(
      "api/category",
      dto,
      {withCredentials: true}
    ).pipe(map(
      res => mapSingleItem<CategoryResponseDto, Category>(res, Category.fromDto)
    ));
  }

  public deleteCategory(categoryId: string): Observable<boolean> {
    return this.http.delete<ApiResponseDto<DeletedItemDto>>(
      `api/category/${categoryId}`,
      {withCredentials: true}
    ).pipe(map(() => true));
  }
}
