import React, { useState } from 'react';

function Frontend() {
  const [searchResult, setSearchResult] = useState(null);
  const [originCity, setOriginCity] = useState('');
  const [destinationCity, setDestinationCity] = useState('');
  const [toCurrency, setToCurrency] = useState('EUR');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const [bookingInfo, setBookingInfo] = useState({ flightId: null, numberOfSeats: 1, passengerName: '', showBookingForm: false });
  const [bookingResponse, setBookingResponse] = useState(null);

  const fetchFlightData = async () => {
    setIsLoading(true);
    setError('');
    const url = `http://localhost:9090/flights/search?originCity=${originCity}&destinationCity=${destinationCity}&toCurrency=${toCurrency}`;

    try {
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      console.log(data);
      setSearchResult(data);
      if(data.length === 0) {
          setError('No results found');
          searchResult([])
      }
    } catch (error) {
      console.error('There was a problem fetching the flight data:', error);
      setError('Failed to fetch flight data. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };
  const bookFlight = async () => {
    // Existing code up to the fetch call...
    const { flightId, numberOfSeats, passengerName } = bookingInfo;
    const url = `http://localhost:9090/flights/book`;

    console.log(flightId, numberOfSeats, passengerName);
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `flightId=${flightId}&numberOfSeats=${numberOfSeats}&passengerName=${encodeURIComponent(passengerName)}`,
      });
  
      const text = await response.text(); // Get response text
      alert('Flight booked successfully!');
      console.log(text); // Log raw response text for debugging
      const data = JSON.parse(text); // Manually parse text to JSON
  
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
  
      console.log('Flight booked successfully!', data);
      alert('Flight booked successfully!');
      // Reset booking info...
    } catch (error) {
      console.error('There was a problem with booking the flight:', error);
    } finally {
      setIsLoading(false);
    }
  };
  
 

  const handleSearchClick = (event) => {
    event.preventDefault();
    fetchFlightData();
  };

  return (
    <div>
      <h1>Flight Search</h1>
      <form>
        <input type="text" value={originCity} onChange={e => setOriginCity(e.target.value)} placeholder="Origin City" />
        <input type="text" value={destinationCity} onChange={e => setDestinationCity(e.target.value)} placeholder="Destination City" />
        <select value={toCurrency} onChange={e => setToCurrency(e.target.value)}>
        <option value="">Select Currency</option>
    <option value="AED">UAE Dirham (AED)</option>
    <option value="ARS">Argentine Peso (ARS)</option>
    <option value="AUD">Australian Dollar (AUD)</option>
    <option value="BGN">Bulgarian Lev (BGN)</option>
    <option value="BRL">Brazilian Real (BRL)</option>
    <option value="BWP">Botswana Pula (BWP)</option>
    <option value="CAD">Canadian Dollar (CAD)</option>
    <option value="CHF">Swiss Franc (CHF)</option>
    <option value="CLP">Chilean Peso (CLP)</option>
    <option value="CNY">Chinese Yuan (CNY)</option>
    <option value="COP">Colombian Peso (COP)</option>
    <option value="DKK">Danish Krone (DKK)</option>
    <option value="EEK">Estonian Kroon (EEK)</option>
    <option value="EGP">Egypt Pounds (EGP)</option>
    <option value="EUR">Euro (EUR)</option>
    <option value="GBP">British Pound (GBP)</option>
    <option value="HKD">Hong Kong Dollar (HKD)</option>
    <option value="HRK">Croatian Kuna (HRK)</option>
    <option value="HUF">Hungarian Forint (HUF)</option>
    <option value="ILS">Israeli New Shekel (ILS)</option>
    <option value="INR">Indian Rupee (INR)</option>
    <option value="ISK">Iceland Krona (ISK)</option>
    <option value="JPY">Japanese Yen (JPY)</option>
    <option value="KRW">South Korean Won (KRW)</option>
    <option value="KZT">Kazakhstani Tenge (KZT)</option>
    <option value="LKR">Sri Lanka Rupee (LKR)</option>
    <option value="LTL">Lithuanian Litas (LTL)</option>
    <option value="LVL">Latvian Lat (LVL)</option>
    <option value="LYD">Libyan Dinar (LYD)</option>
    <option value="MXN">Mexican Peso (MXN)</option>
    <option value="MYR">Malaysian Ringgit (MYR)</option>
    <option value="NOK">Norwegian Kroner (NOK)</option>
    <option value="NPR">Nepalese Rupee (NPR)</option>
    <option value="NZD">New Zealand Dollar (NZD)</option>
    <option value="OMR">Omani Rial (OMR)</option>
    <option value="PKR">Pakistan Rupee (PKR)</option>
    <option value="QAR">Qatari Rial (QAR)</option>
    <option value="RON">Romanian Leu (RON)</option>
    <option value="RUB">Russian Ruble (RUB)</option>
    <option value="SAR">Saudi Riyal (SAR)</option>
    <option value="SDG">Sudanese Pound (SDG)</option>
    <option value="SEK">Swedish Krona (SEK)</option>
    <option value="SGD">Singapore Dollar (SGD)</option>
    <option value="THB">Thai Baht (THB)</option>
    <option value="TRY">Turkish Lira (TRY)</option>
    <option value="TTD">Trinidad/Tobago Dollar (TTD)</option>
    <option value="TWD">Taiwan Dollar (TWD)</option>
    <option value="UAH">Ukrainian Hryvnia (UAH)</option>
    <option value="USD">United States Dollar (USD)</option>
    <option value="VEB">Venezuelan Bolivar (VEB)</option>
    <option value="ZAR">South African Rand (ZAR)</option>
</select>
        <button onClick={handleSearchClick}>Search Flights</button>
      </form>
      {isLoading && <p>Loading...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {searchResult && (
        <>
          <h2>Search Results for {searchResult.originCity} to {searchResult.destinationCity}</h2>
          <div>
            <h3>Driving Details</h3>
            <p>Distance: {searchResult.drivingDetails.route.distance} km</p>
            <p>Time: {searchResult.drivingDetails.route.formattedTime}</p>
          </div>
          <div>
            <h3>Images</h3>
            <div style={{ display: 'flex', flexWrap: 'wrap' }}>
              {searchResult.images.map((image, idx) => (
                <div key={idx} style={{ margin: '10px' }}>
                  <img src={image.imageUrl} alt={image.title} style={{ width: '200px', height: '200px' }} />
                  <p>{image.title}</p>
                </div>
              ))}
            </div>
          </div>
          <table>
            <thead>
              <tr>
                <th>Airline</th>
                <th>Seats Available</th>
                <th>Connections</th>
                <th>Price</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {searchResult.flights.map((flight, index) => (
                <tr key={index}>
                  <td>{flight.airline}</td>
                  <td>{flight.availableSeats}</td>
                  <td>{flight.numberOfConnections}</td>
                  <td>{toCurrency} {flight.convertedPrice.toFixed(2)}</td>
                  <td>
                    <button onClick={() => setBookingInfo({ ...bookingInfo, flightId: flight.flightId, showBookingForm: true })}>
                      Book This Flight
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
      {bookingInfo.showBookingForm && (
        <div>
        <h2>Book Flight</h2>
        <form onSubmit={(e) => { e.preventDefault(); bookFlight(); }}>
          <input type="text" placeholder="Passenger Name" value={bookingInfo.passengerName} onChange={(e) => setBookingInfo({ ...bookingInfo, passengerName: e.target.value })} required />
          <input type="number" placeholder="Number of Seats" value={bookingInfo.numberOfSeats} min="1" onChange={(e) => setBookingInfo({ ...bookingInfo, numberOfSeats: parseInt(e.target.value, 10) })} required />
          <button type="submit">Confirm Booking</button>
        </form>
      </div>
      )}
      {bookingResponse && (
        <div>
          <p>{bookingResponse}</p>
        </div>
      )}
    </div>
  );
}

export default Frontend;