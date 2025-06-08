import { IMapper } from '@/common/types/IMapper'
import { Expense, ExpenseList } from '../types/Expense'

export class ExpensesMapper implements IMapper<Expense, ExpenseList> {
  map(baseType: Expense): ExpenseList {
    return {
      id: baseType.id,
      value: baseType.value,
      description: baseType.description,
      date: baseType.date,
      category: {
        id: baseType.category.id,
        name: baseType.category.name,
      },
      paymentType: baseType.paymentType,
      address: baseType.address,
    }
  }
}
