import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from '../model/domain/User';
import { ApiResponseDto } from '../model/dto/rest/ApiResponse.dto';
import { SingleItemDto } from '../model/dto/rest/SingleItem.dto';
import { UserResponseDto } from '../model/dto/UserResponse.dto';
import { mapSingleItem } from '../model/mapper/SingleItem.mapper';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {
  }

  public login(email: string, password: string): Observable<User> {
    return this.http.post<ApiResponseDto<SingleItemDto<UserResponseDto>>>(
      "api/auth/login",
      {email, password},
      {withCredentials: true}
    ).pipe(map(res => mapSingleItem<UserResponseDto, User>(res, User.fromDto)));
  }
}
