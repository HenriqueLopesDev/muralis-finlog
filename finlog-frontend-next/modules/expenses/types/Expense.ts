import { Address } from '@/modules/addresses/types/Address'
import { Category, CategorySummary } from '@/modules/categories/types/Category'
import { PaymentType } from '@/modules/paymentTypes/types/PaymentType'

export interface Expense {
  id: number
  value: number
  description: string
  date: string
  category: Category
  paymentType: PaymentType
  address: Address
}

export type ExpenseList = Omit<Expense, 'category'> & {
  category: CategorySummary
}
