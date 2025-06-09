import { ExpenseList } from '@/modules/expenses/types/Expense'

export interface ExpensesTableProps {
  rows: ExpenseList[]
  deleteExpenseModalDispatcher: React.Dispatch<
    React.SetStateAction<{
      open: boolean
      expenseId: number
    }>
  >
}
