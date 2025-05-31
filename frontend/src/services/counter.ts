import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const counterApi = createApi({
  baseQuery: fetchBaseQuery({ baseUrl: '/api' }),
  endpoints: (builder) => ({
    getCount: builder.query({
      query: () => '/counter',
    }),
  }),
  initialState: {
    counter: 0,
  },
});

export const { useGetCountQuery } = counterApi
