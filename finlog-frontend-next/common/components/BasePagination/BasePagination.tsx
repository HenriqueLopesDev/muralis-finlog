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
    setPaginationData((prev: unknown) => {
      if (prev) {
        return { ...prev, current_page: value }
      }
      return prev
    })
  }

  return (
    <div className="flex justify-end p-[0.7rem_0rem] max-[992px]:justify-center">
      <Pagination
        count={pagination?.last_page}
        color="primary"
        showFirstButton
        showLastButton
        onChange={handleChangePage}
        page={pagination?.currentPage}
        siblingCount={0}
        sx={PaginationStyles}
      />
    </div>
  )
}
