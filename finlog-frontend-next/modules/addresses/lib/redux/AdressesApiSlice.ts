import { apiSlice } from '@/common/lib/redux/apiSlice'
import { SuccessResponse } from '@/common/types/api/ApiResponse'
import { Address } from '../../types/Address'

const addressesApiSlice = apiSlice.injectEndpoints({
  overrideExisting: true,
  endpoints: (builder) => ({
    getAddressByZipCode: builder.query<Address, string>({
      query: (zipCode) => ({
        url: `/enderecos/${zipCode}`,
        method: 'GET',
      }),
      transformResponse: (response: SuccessResponse<Address>) => response.data,
    }),
  }),
})

export const { useLazyGetAddressByZipCodeQuery } = addressesApiSlice
