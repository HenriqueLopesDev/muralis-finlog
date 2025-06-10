export interface UpdateExpenseModalProps {
  updateExpenseModalState: {
    open: boolean
    expenseId: number | undefined
  }
  setUpdateExpenseModalState: React.Dispatch<
    React.SetStateAction<{
      open: boolean
      expenseId: number | undefined
    }>
  >
  callBackFn?: () => void
}
