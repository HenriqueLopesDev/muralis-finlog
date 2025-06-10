import { IMapper } from '@/common/types/IMapper'
import { Expense, ExpenseList } from '../types/Expense'
import { PaginatedData, PaginatedResponse } from '@/common/types/api/ApiResponse'

export class ExpensesMapper implements IMapper<PaginatedResponse<Expense>, PaginatedData<ExpenseList>> {
  map(baseType: PaginatedResponse<Expense>): PaginatedData<ExpenseList> {
    return {
      content: baseType.data.content.map((expense) => this.mapExpense(expense)),
      pagination: baseType.data.pagination
    }
  }
  
  private mapExpense(expense: Expense): ExpenseList {
    return {
      id: expense.id,
      value: expense.value,
      description: expense.description,
      date: expense.date,
      category: {
        id: expense.category.id,
        name: expense.category.name,
      },
      paymentType: expense.paymentType,
      address: expense.address,
    }
  }
}
