interface BaseApiResponse {
  success: boolean
}

export interface SuccessResponse<T> extends BaseApiResponse {
  data: T
}

export interface ErrorResponse extends BaseApiResponse {
  error: string
}
