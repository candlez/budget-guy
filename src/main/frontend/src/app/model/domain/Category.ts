import { CategoryResponseDto } from '../dto/CategoryResponse.dto';


export class Category {
  constructor(
    public categoryId: string,
    public userId: string,
    public name: string,
    public description: string,
    public createdAt: Date
  ) {
  }

  public static fromDto(dto: CategoryResponseDto): Category {
    return new Category(
      dto.categoryId,
      dto.userId,
      dto.name,
      dto.description,
      new Date(dto.createdAt)
    )
  }
}
