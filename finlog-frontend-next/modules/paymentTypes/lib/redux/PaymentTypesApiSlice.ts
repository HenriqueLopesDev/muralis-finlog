import { apiSlice } from '@/common/lib/redux/apiSlice'
import { PaymentType } from '@/modules/paymentTypes/types/PaymentType'
import { SuccessResponse } from '@/common/types/api/ApiResponse'

const paymentTypesApiSlice = apiSlice.injectEndpoints({
  overrideExisting: true,
  endpoints: (builder) => ({
    getPaymentTypes: builder.query<PaymentType[], void>({
      query: () => ({
        url: '/tipos-pagamento',
        method: 'GET',
      }),
      transformResponse: (response: SuccessResponse<PaymentType[]>) =>
        response.data,
    }),
  }),
})

export const { useLazyGetPaymentTypesQuery } = paymentTypesApiSlice
