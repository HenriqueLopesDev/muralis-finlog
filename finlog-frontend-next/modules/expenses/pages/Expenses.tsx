'use client'

import { Divider } from '@mui/material'
import { ExpensesTable } from '../components/ExpensesTable/ExpensesTable'
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline'
import { BaseButton } from '@/common/components/BaseButton/BaseButton'
import { BasePagination } from '@/common/components/BasePagination/BasePagination'
import {
  useGetExpensesQuery,
  useGetTotalExpensesValueQuery,
} from '@/modules/expenses/lib/redux/ExpensesApiSlice'
import { LoadingSpinner } from '@/common/components/LoadingSpinner/LoadingSpinner'
import React from 'react'
import { DeleteExpenseModal } from '../components/DeleteExpenseModal/DeleteExpenseModal'
import { CreateExpenseModal } from '../components/CreateExpenseModal/CreateExpenseModal'
import { PaginationMeta } from '@/common/types/api/ApiResponse'
import { DateFilter } from '../components/DateFilter/DateFIlter'
import { UpdateExpenseModal } from '../components/UpdateExpenseModal/UpdateExpenseModal'
import { formatToBrazilianCurrency } from '@/common/utils/formatters'

export function Expenses() {
  const initialDates = React.useMemo(() => {
    const today = new Date()
    const yyyy = today.getFullYear()
    const mm = String(today.getMonth() + 1).padStart(2, '0')
    const dd = String(today.getDate()).padStart(2, '0')

    const firstDayOfMonth = `${yyyy}-${mm}-01`
    const currentDay = `${yyyy}-${mm}-${dd}`

    return {
      minDate: firstDayOfMonth,
      maxDate: currentDay,
    }
  }, [])

  const [paginationData, setPaginationData] = React.useState<PaginationMeta>()
  const [filterDates, setFilterDates] = React.useState(initialDates)
  const { data: totalValue } = useGetTotalExpensesValueQuery({
    minDate: filterDates.minDate,
    maxDate: filterDates.maxDate,
  })
  const { data: expenses, isLoading } = useGetExpensesQuery(
    {
      page: paginationData?.currentPage ?? 1,
      minDate: filterDates.minDate,
      maxDate: filterDates.maxDate,
    },
    {
      refetchOnMountOrArgChange: true,
    },
  )

  const [deleteExpenseModalState, setDeleteExpenseModalState] = React.useState({
    open: false,
    expenseId: 0,
  })

  const [createExpenseModalState, setCreateExpenseModalState] =
    React.useState(false)

  const [updateExpenseModalState, setUpdateExpenseModalState] = React.useState<{
    open: boolean
    expenseId: number | undefined
  }>({
    open: false,
    expenseId: undefined,
  })

  React.useEffect(() => {
    if (expenses?.pagination) {
      setPaginationData(expenses.pagination)
    }
  }, [expenses?.pagination])

  if (isLoading) {
    return <LoadingSpinner />
  }

  return (
    <div className="flex flex-col justify-between w-full">
      <div className="flex flex-col gap-3">
        <div className="font-montserrat flex gap-2 items-center max-[768px]:justify-center">
          <h2 className="text-[14px] uppercase font-medium">
            Total de despesas:{' '}
          </h2>
          <span className="text-[18px] uppercase font-medium">
            {formatToBrazilianCurrency(totalValue ?? 0)}
          </span>
        </div>
        <Divider />
        <div className="flex gap-2 justify-between items-center max-[768px]:flex-col max-[768px]:justify-start max-[768px]:items-start">
          <DateFilter
            setPaginationData={setPaginationData}
            setFilterDates={setFilterDates}
            minDate={filterDates.minDate}
            maxDate={filterDates.maxDate}
          />
          <BaseButton
            onClick={() => setCreateExpenseModalState(true)}
            className="h-[38px]"
          >
            <AddCircleOutlineIcon />
            Nova despesa
          </BaseButton>
        </div>
        <div>
          <ExpensesTable
            rows={expenses?.content ?? []}
            deleteExpenseModalDispatcher={setDeleteExpenseModalState}
            updateExpenseModalDispatcher={setUpdateExpenseModalState}
          />
        </div>
      </div>
      <div className=" flex justify-end max-[768px]:justify-center">
        <BasePagination
          pagination={paginationData}
          setPaginationData={setPaginationData}
        />
      </div>
      <DeleteExpenseModal
        deleteModalState={deleteExpenseModalState}
        setDeleteModalState={setDeleteExpenseModalState}
      />
      <CreateExpenseModal
        createExpenseModalState={createExpenseModalState}
        setCreateExpenseModalState={setCreateExpenseModalState}
      />
      <UpdateExpenseModal
        updateExpenseModalState={updateExpenseModalState}
        setUpdateExpenseModalState={setUpdateExpenseModalState}
      />
    </div>
  )
}
