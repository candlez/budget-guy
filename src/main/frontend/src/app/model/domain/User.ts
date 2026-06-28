import { UserResponseDto } from '../dto/UserResponse.dto';

export class User {
  constructor(
    public userId: string,
    public email: string,
    public firstName: string,
    public lastName: string,
    public createdAt: Date
  ) {
  }

  static fromDto(dto: UserResponseDto): User {
    return new User(
      dto.userId,
      dto.email,
      dto.firstName,
      dto.lastName,
      new Date(dto.createdAt)
    );
  }
}
