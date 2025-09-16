import HelloWorld from './components/HelloWorld'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import 'bootstrap/dist/js/bootstrap.esm'

function App() {
  return (
    <>
      <header>
        <nav className="navbar navbar-dark bg-dark">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              <i className="bi bi-globe"></i> &nbsp; Example
            </a>
          </div>
        </nav>
      </header>
      <main>
        <HelloWorld msg="Modern mainstream web application example" />
      </main>
      <footer className="text-muted py-5">
        <div className="container">
          <p className="float-end mb-1">
            <a href="#">Back to top</a>
          </p>
        </div>
      </footer>
    </>
  )
}

export default App
