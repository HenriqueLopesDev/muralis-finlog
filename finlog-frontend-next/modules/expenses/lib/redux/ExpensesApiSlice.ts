import { apiSlice } from '@/common/lib/redux/apiSlice'
import { CreateExpenseRequest, Expense, ExpenseList } from '../../types/Expense'
import {
  PaginatedData,
  PaginatedResponse,
  SuccessResponse,
} from '@/common/types/api/ApiResponse'
import { ExpensesMapper } from '../../mappers/ExpensesMapper'

const expensesMapper = new ExpensesMapper()

interface ExpensesQueryParams {
  page: number
  minDate: string
  maxDate: string
}

const expensesApiSlice = apiSlice.injectEndpoints({
  overrideExisting: true,
  endpoints: (builder) => ({
    getExpenses: builder.query<PaginatedData<ExpenseList>, ExpensesQueryParams>(
      {
        query: ({ page, minDate, maxDate }) => ({
          url: '/despesas',
          method: 'GET',
          params: {
            page,
            startDate: minDate,
            endDate: maxDate,
          },
        }),
        transformResponse: (response: PaginatedResponse<Expense>) =>
          expensesMapper.map(response),
        providesTags: ['Expenses'],
      },
    ),

    getExpenseById: builder.query<Expense, number>({
      query: (expenseId) => ({
        url: `/despesas/${expenseId}`,
        method: 'GET',
      }),
      transformResponse: (response: SuccessResponse<Expense>) => response.data,
    }),

    getTotalExpensesValue: builder.query<
      number,
      Omit<ExpensesQueryParams, 'page'>
    >({
      query: ({ minDate, maxDate }) => ({
        url: '/despesas/total',
        method: 'GET',
        params: {
          startDate: minDate,
          endDate: maxDate,
        },
      }),
      transformResponse: (response: SuccessResponse<number>) => response.data,
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

    updateExpense: builder.mutation<
      void,
      { id: number; data: CreateExpenseRequest }
    >({
      query: ({ id, data }) => ({
        url: `/despesas/${id}`,
        method: 'PUT',
        body: data,
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
  useLazyGetExpenseByIdQuery,
  useUpdateExpenseMutation,
  useGetTotalExpensesValueQuery,
} = expensesApiSlice
