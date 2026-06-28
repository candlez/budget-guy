import {ApiResponseDto} from '../dto/rest/ApiResponse.dto';
import {SingleItemDto} from '../dto/rest/SingleItem.dto';

export function mapSingleItem<T, J>(res: ApiResponseDto<SingleItemDto<T>>, mapFunc: (dte: T) => J): J {
  return mapFunc(res.data.items[0]);
}
