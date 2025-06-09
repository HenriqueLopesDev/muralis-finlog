export interface DeleteExpenseModalProps {
  open: {
    open: boolean
    expenseId: number
  }
  setOpen: React.Dispatch<
    React.SetStateAction<{
      open: boolean
      expenseId: number
    }>
  >
  callBackFn: () => void
}
