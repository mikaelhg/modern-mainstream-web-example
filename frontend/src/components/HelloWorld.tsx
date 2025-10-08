import { useGetCountQuery } from '../services/counter'

function HelloWorld({ msg }: { msg: string }) {
  const { data, isLoading } = useGetCountQuery(undefined, {
    pollingInterval: 1000,
  })

  if (isLoading) return <div className="text-center py-5">Loading...</div>
  if (!data) return <div className="text-center py-5">Missing data!</div>

  return (
    <section className="py-5 text-center container mx-auto px-4">
      <div className="py-5">
        <div className="max-w-2xl mx-auto">
          <h1 className="font-light text-3xl">{msg}</h1>
          <p className="text-gray-500 mt-2">The count is: {data.counter}.</p>
        </div>
      </div>
    </section>
  )
}
export default HelloWorld
