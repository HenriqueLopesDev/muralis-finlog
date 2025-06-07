import React from 'react'

interface PaginationProps {
  last_page: number
  currentPage: number
}

export interface MainPaginationProps {
  pagination: PaginationProps
  setPaginationData: React.Dispatch<React.SetStateAction<unknown>>
}
