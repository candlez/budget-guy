

export interface ApiResponseDto<T> {
  status: 'success' | 'error';
  data: T;
}
