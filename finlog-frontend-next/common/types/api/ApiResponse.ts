interface BaseApiResponse {
  success: boolean
}

export interface SuccessResponse<T> extends BaseApiResponse {
  data: T
}

export interface ErrorResponse extends BaseApiResponse {
  error: string
}

export interface PaginationMeta {
  currentPage: number
  totalPages?: number
  totalItems?: number
}

export interface PaginatedData<T> {
  content: T[]
  pagination: PaginationMeta
}

export type PaginatedResponse<T> = SuccessResponse<PaginatedData<T>>
