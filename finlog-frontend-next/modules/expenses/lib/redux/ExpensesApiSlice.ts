import { apiSlice } from '@/common/lib/redux/apiSlice'
import { CreateExpenseRequest, Expense, ExpenseList } from '../../types/Expense'
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
      providesTags: ['Expenses'],
    }),

    createExpense: builder.mutation<void, CreateExpenseRequest>({
      query: (expense) => ({
        url: '/despesas',
        method: 'POST',
        body: expense,
      }),
      invalidatesTags: ['Expenses'],
    }),

    deleteExpense: builder.mutation<void, number>({
      query: (expenseId) => ({
        url: `/despesas/${expenseId}`,
        method: 'DELETE',
      }),
      invalidatesTags: ['Expenses'],
    }),
  }),
})

export const {
  useGetExpensesQuery,
  useDeleteExpenseMutation,
  useCreateExpenseMutation,
} = expensesApiSlice
