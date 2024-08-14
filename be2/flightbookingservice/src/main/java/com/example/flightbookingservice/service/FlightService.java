package com.example.flightbookingservice.service;

import com.example.flightbookingservice.dto.*;
import com.example.flightbookingservice.model.Booking;
import com.example.flightbookingservice.model.Flight;
import com.example.flightbookingservice.repository.BookingRepository;
import com.example.flightbookingservice.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    private final CurrencyConversionService currencyConversionService;

    public MapQuestResponse getDrivingDetails(String from, String to) {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "k2jepj8dkTkhts7EXyVOue3ilm0KBqz4"; // Replace with your actual MapQuest API key
        String url = String.format("https://www.mapquestapi.com/directions/v2/route?key=%s&from=%s&to=%s", apiKey, from, to);
        ResponseEntity<MapQuestResponse> response = restTemplate.getForEntity(url, MapQuestResponse.class);
        return response.getBody();
    }






    @Autowired
    public FlightService(FlightRepository flightRepository, BookingRepository bookingRepository, CurrencyConversionService currencyConversionService, FlickrService flickrService) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
        this.currencyConversionService = currencyConversionService;
        this.flickrService = flickrService;
    }

    private final FlickrService flickrService;






    public ConsolidatedSearchResponse searchFlightsWithDetails(String originCity, String destinationCity, String toCurrency) {
        List<Flight> flights = flightRepository.findByOriginCityAndDestinationCity(originCity, destinationCity);
        List<FlickrImage> images = flickrService.getImagesForCity(destinationCity); // Assuming this call works as expected
        MapQuestResponse drivingDetails = getDrivingDetails(originCity, destinationCity); // Adjust this to your actual call

        Double rate = 1.0;
        if (!"USD".equals(toCurrency)) {
            rate = currencyConversionService.getConversionRate("USD", toCurrency);
        }

        List<FlightSearchResponse> flightResponses = new ArrayList<>();
        for (Flight flight : flights) {
            FlightSearchResponse response = new FlightSearchResponse();
            response.setFlightId(flight.getId());
            response.setOriginCity(flight.getOriginCity());
            response.setDestinationCity(flight.getDestinationCity());
            response.setAirline(flight.getAirline());
            response.setAvailableSeats(flight.getAvailableSeats());
            response.setNumberOfConnections(flight.getNumberOfConnections());
            response.setTicketPrice(flight.getTicketPrice()); // Original ticket price
            response.setConvertedPrice(flight.getTicketPrice() * rate); // Converted ticket price

            // Assuming you have a way to set driving details and images for each flight
            // This might involve modifying your FlightSearchResponse constructor or adding setters
            flightResponses.add(response);
        }

        // Assuming ConsolidatedSearchResponse constructor or a setter method can accept these parameters
        return new ConsolidatedSearchResponse(originCity, destinationCity, drivingDetails, images, flightResponses);
    }






    @Transactional
    public Optional<Booking> bookFlight(Long flightId, int numberOfSeats, String passengerName) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            if (flight.bookSeats(numberOfSeats, passengerName)) {
                Booking booking = new Booking(passengerName, numberOfSeats, flight);
                return Optional.of(bookingRepository.save(booking));
            }
        }
        return Optional.empty();
    }
}
