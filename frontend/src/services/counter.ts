import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const counterApi = createApi({
  baseQuery: fetchBaseQuery({ baseUrl: '/api' }),
  endpoints: (builder) => ({
    getCount: builder.query({
      query: () => '/counter',
    }),
  }),
});

export const { useGetCountQuery } = counterApi
