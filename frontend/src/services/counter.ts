import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export interface CounterResponse {
  counter: number
}

export const counterApi = createApi({
  baseQuery: fetchBaseQuery({ baseUrl: '/api' }),
  endpoints: (builder) => ({
    getCount: builder.query<CounterResponse, void>({
      query: () => '/counter',
    }),
  }),
})

export const { useGetCountQuery } = counterApi
