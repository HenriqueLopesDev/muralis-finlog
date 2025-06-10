import * as React from 'react'
import { DeleteExpenseModalProps } from './DeleteExpenseModalProps'
import { BaseModal } from '@/common/components/BaseModal/BaseModal'
import { ActionAlert } from '@/common/utils/ActionAlert'
import { useDeleteExpenseMutation } from '../../lib/redux/ExpensesApiSlice'

export function DeleteExpenseModal({
  deleteModalState,
  setDeleteModalState,
}: DeleteExpenseModalProps) {
  const [deleteExpenseMutation] = useDeleteExpenseMutation()

  const handleClose = () => {
    setDeleteModalState({
      expenseId: 0,
      open: false,
    })
  }

  const handleDeleteExpense = async () => {
    const { error } = await deleteExpenseMutation(deleteModalState.expenseId)

    if (error) {
      return await ActionAlert.show({
        icon: 'error',
        title: 'Erro ao excluir despesa, tente novamente mais tarde.',
      })
    }

    handleClose()
    await ActionAlert.show({
      icon: 'success',
      title: 'Despesa excluída com sucesso!',
    })
  }

  return (
    <BaseModal
      open={deleteModalState.open}
      onClose={handleClose}
      maxWidth="xs"
      title="Excluir despesa"
      actions={
        <>
          <button
            className="h-10 border border-[var(--color-01)] hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] bg-transparent px-3 py-2 font-poppins font-semibold text-[var(--color-01)] transition-colors duration-200 hover:bg-[var(--color-02)] hover:text-white"
            onClick={handleClose}
          >
            Cancelar
          </button>
          <button
            className="hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] px-3 py-2 font-poppins font-semibold text-white transition-colors duration-200 bg-red-700 hover:bg-red-800"
            onClick={handleDeleteExpense}
          >
            Excluir
          </button>
        </>
      }
    >
      <p className="text-center font-poppins">
        Deseja realmente excluir essa despesa? Atenção, essa ação é{' '}
        <strong>irreversível</strong>.
      </p>
    </BaseModal>
  )
}
