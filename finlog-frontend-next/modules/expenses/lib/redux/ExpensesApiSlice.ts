import { apiSlice } from '@/common/lib/redux/apiSlice'
import { CreateExpenseRequest, Expense, ExpenseList } from '../../types/Expense'
import { PaginatedData, PaginatedResponse, SuccessResponse } from '@/common/types/api/ApiResponse'
import { ExpensesMapper } from '../../mappers/ExpensesMapper'

const expensesMapper = new ExpensesMapper()

const expensesApiSlice = apiSlice.injectEndpoints({
  overrideExisting: true,
  endpoints: (builder) => ({
    getExpenses: builder.query<PaginatedData<ExpenseList>, number>({
      query: (page: number) => ({
        url: `/despesas?page=${page}`,
        method: 'GET',
      }),
      transformResponse: (response: PaginatedResponse<Expense>) =>
        expensesMapper.map(response),
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
