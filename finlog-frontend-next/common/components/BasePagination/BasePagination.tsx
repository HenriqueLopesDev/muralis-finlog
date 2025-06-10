import React from 'react'

import { Pagination } from '@mui/material'
import { PaginationStyles } from './BasePagination.style'
import { BasePaginationProps } from './BasePaginationProps'

export function BasePagination({
  pagination,
  setPaginationData,
}: BasePaginationProps) {
  const handleChangePage = (
    event: React.ChangeEvent<unknown>,
    value: number,
  ) => {
    setPaginationData((prev) => {
      if (prev) {
        return { ...prev, currentPage: value }
      }
      return prev
    })
  }

  return (
    <div className="flex justify-end p-[0.6rem_0rem]">
      <Pagination
        count={pagination?.totalPages ?? 1}
        color="primary"
        showFirstButton
        showLastButton
        onChange={handleChangePage}
        page={pagination?.currentPage ?? 1}
        siblingCount={0}
        sx={PaginationStyles}
      />
    </div>
  )
}
