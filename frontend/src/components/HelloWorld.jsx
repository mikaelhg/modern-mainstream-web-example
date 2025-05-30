import React, { useEffect, useState } from 'react';

function HelloWorld({ msg }) {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const apiCall = () => {
      fetch('/api/counter')
        .then((response) => response.json())
        .then((data) => setCount(data.counter));
    };
    apiCall();
    const interval = setInterval(apiCall, 1000);
    return () => clearInterval(interval);
  }, []);

  return (
    <section className="py-5 text-center container">
      <div className="row py-lg-5">
        <div className="col-lg-6 col-md-8 mx-auto">
          <h1 className="fw-light">{msg}</h1>
          <p className="lead text-muted">
            The count is: {count}.
          </p>
        </div>
      </div>
    </section>
  );
}

export default HelloWorld;
