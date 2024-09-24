import React, { useState, useEffect } from 'react';

function StockPrice() {
  const [price, setPrice] = useState(null);

  useEffect(() => {
    // Récupérer le prix initial
    fetch('http://localhost:8080/api/stock-price')
      .then(response => response.json())
      .then(data => setPrice(data));

    // Configurer SSE
    const eventSource = new EventSource('http://localhost:8080/api/price-stream');
    eventSource.onmessage = (event) => {
      setPrice(JSON.parse(event.data));
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div>
      <h1>Prix de l'action en temps réel</h1>
      <p>Prix actuel : {price}</p>
    </div>
  );
}

export default StockPrice;
