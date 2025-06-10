import { PaginationMeta } from '@/common/types/api/ApiResponse'
import React from 'react'

export interface BasePaginationProps {
  pagination: PaginationMeta | undefined
  setPaginationData: React.Dispatch<
    React.SetStateAction<PaginationMeta | undefined>
  >
}
