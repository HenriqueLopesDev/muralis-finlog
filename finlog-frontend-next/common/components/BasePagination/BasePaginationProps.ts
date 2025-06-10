import { PaginationMeta } from '@/common/types/api/ApiResponse'
import React from 'react'

interface PaginationProps {
  last_page: number
  currentPage: number
}

export interface MainPaginationProps {
  pagination: PaginationMeta | undefined
  setPaginationData: React.Dispatch<React.SetStateAction<PaginationMeta | undefined>>
}
