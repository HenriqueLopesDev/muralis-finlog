export interface CreateExpenseModalProps {
  createExpenseModalState: boolean
  setCreateExpenseModalState: React.Dispatch<React.SetStateAction<boolean>>
  callBackFn?: () => void
}
