export interface DeleteExpenseModalProps {
  deleteModalState: {
    open: boolean
    expenseId: number
  }
  setDeleteModalState: React.Dispatch<
    React.SetStateAction<{
      open: boolean
      expenseId: number
    }>
  >
}
