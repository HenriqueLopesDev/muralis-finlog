import React from 'react'

import { Pagination } from '@mui/material'
import { MainPaginationProps } from './BasePaginationProps'
import { PaginationStyles } from './BasePagination.style'

export function MainPagination({
  pagination,
  setPaginationData,
}: MainPaginationProps) {
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
    <div className="flex justify-end p-[0.6rem_0rem] max-[992px]:justify-center">
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
