import { apiSlice } from '@/common/lib/redux/apiSlice'
import { Expense, ExpenseList } from '../../types/Expense'
import { SuccessResponse } from '@/common/types/api/ApiResponse'
import { ExpensesMapper } from '../../mappers/ExpensesMapper'

const expensesMapper = new ExpensesMapper()

const expensesApiSlice = apiSlice.injectEndpoints({
  overrideExisting: true,
  endpoints: (builder) => ({
    getExpenses: builder.query<ExpenseList[], void>({
      query: () => ({
        url: '/despesas',
        method: 'GET',
      }),
      transformResponse: (response: SuccessResponse<Expense[]>) =>
        response.data.map((expense) => expensesMapper.map(expense)),
    }),
  }),
})

export const { useGetExpensesQuery } = expensesApiSlice
