import './App.css';
import HelloWorld from './components/HelloWorld'

function App() {
  return (
    <>
      <header>
        <nav className="bg-gray-800 text-white">
          <div className="container mx-auto px-4 py-2">
            <a className="flex items-center text-xl font-bold" href="#">
              <span>🌍</span>
              <span className="ml-2">Example</span>
            </a>
          </div>
        </nav>
      </header>
      <main>
        <HelloWorld msg="Modern mainstream web application example" />
      </main>
      <footer className="text-gray-500 py-5">
        <div className="container mx-auto px-4">
          <p className="float-right mb-1">
            <a href="#" className="hover:text-gray-700">Back to top</a>
          </p>
        </div>
      </footer>
    </>
  )
}
export default App