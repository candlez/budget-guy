import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Category } from '../model/domain/Category';
import { CategoryResponseDto } from '../model/dto/CategoryResponse.dto';
import { ApiResponseDto } from '../model/dto/rest/ApiResponse.dto';
import { ListItemDto } from '../model/dto/rest/ListItem.dto';

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
}
