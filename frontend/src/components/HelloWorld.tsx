import { useGetCountQuery } from '../services/counter'

function HelloWorld({ msg }) {
  const { data, error, isLoading, isFetching, refetch } = useGetCountQuery(
    undefined,
    { pollingInterval: 1000 },
  )

  if (isLoading) return <div>Loading...</div>
  if (!data) return <div>Missing data!</div>

  return (
    <section className="py-5 text-center container">
      <div className="row py-lg-5">
        <div className="col-lg-6 col-md-8 mx-auto">
          <h1 className="fw-light">{msg}</h1>
          <p className="lead text-muted">The count is: {data.counter}.</p>
        </div>
      </div>
    </section>
  )
}

export default HelloWorld
