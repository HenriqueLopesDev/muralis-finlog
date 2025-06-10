'use client'

import { Divider } from '@mui/material'
import { ExpensesTable } from '../components/ExpensesTable/ExpensesTable'
import { BaseInput } from '@/common/components/BaseInput/BaseInput'
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline'
import { BaseButton } from '@/common/components/BaseButton/BaseButton'
import { MainPagination } from '@/common/components/BasePagination/BasePagination'
import {
  useDeleteExpenseMutation,
  useGetExpensesQuery,
} from '@/modules/expenses/lib/redux/ExpensesApiSlice'
import { LoadingSpinner } from '@/common/components/LoadingSpinner/LoadingSpinner'
import React from 'react'
import { ActionAlert } from '@/common/utils/ActionAlert'
import { DeleteExpenseModal } from '../components/DeleteExpenseModal/DeleteExpenseModal'
import { CreateExpenseModal } from '../components/CreateExpenseModal/CreateExpenseModal'

export function Expenses() {
  const { data: expenses, isLoading } = useGetExpensesQuery()
  const [deleteExpenseMutation] = useDeleteExpenseMutation()

  const [deleteExpenseModalState, setDeleteExpenseModalState] = React.useState({
    open: false,
    expenseId: 0,
  })

  const [createExpenseModalState, setCreateExpenseModalState] =
    React.useState(false)

  if (isLoading) {
    return <LoadingSpinner />
  }

  const handleDeleteExpense = async () => {
    const { error } = await deleteExpenseMutation(
      deleteExpenseModalState.expenseId,
    )

    if (error) {
      return await ActionAlert.show({
        icon: 'error',
        title: 'Erro ao excluir despesa, tente novamente mais tarde.',
      })
    }

    await ActionAlert.show({
      icon: 'success',
      title: 'Despesa excluída com sucesso!',
    })
  }

  return (
    <div className="flex flex-col justify-between w-full">
      <div className="flex flex-col gap-3">
        <div className="font-montserrat flex gap-2 items-center">
          <h2 className="text-[14px] uppercase font-medium">
            Total de despesas:{' '}
          </h2>
          <span className="text-[18px] uppercase font-medium">R$ 0,00</span>
        </div>
        <Divider />
        <div className="flex gap-2 justify-between items-center">
          <form>
            <BaseInput type="date" />
            <BaseInput type="date" />
            <button className="bg-blue-500 text-white rounded p-2">
              Buscar
            </button>
          </form>
          <BaseButton onClick={() => setCreateExpenseModalState(true)}>
            <AddCircleOutlineIcon />
            Nova despesa
          </BaseButton>
        </div>
        <div>
          <ExpensesTable
            rows={expenses ?? []}
            deleteExpenseModalDispatcher={setDeleteExpenseModalState}
          />
        </div>
      </div>
      <div className=" flex justify-end">
        <MainPagination
          pagination={{ last_page: 10, currentPage: 1 }}
          setPaginationData={() => {}}
        />
      </div>
      <DeleteExpenseModal
        open={deleteExpenseModalState}
        setOpen={setDeleteExpenseModalState}
        callBackFn={handleDeleteExpense}
      />
      <CreateExpenseModal
        open={createExpenseModalState}
        setOpen={setCreateExpenseModalState}
      />
    </div>
  )
}
