import { useState } from "react";

function App() {

  const [data, setData] = useState(null);

  const callApi = async () => {

    const response = await fetch("/orders/hello");

    const json = await response.json();

    setData(json);
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1>Gateway Test</h1>

      <button onClick={callApi}>
        Call Order Service
      </button>

      {data && (
        <pre>{JSON.stringify(data, null, 2)}</pre>
      )}

    </div>
  );
}

export default App;