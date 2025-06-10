export interface CreateExpenseModalProps {
  open: boolean
  setOpen: React.Dispatch<React.SetStateAction<boolean>>
  callBackFn?: () => void
}
