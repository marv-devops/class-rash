package com.example.flightbookingservice.controller;

import com.example.flightbookingservice.dto.ConsolidatedSearchResponse;
import com.example.flightbookingservice.dto.FlightSearchResponse;
import com.example.flightbookingservice.model.Booking;
import com.example.flightbookingservice.model.Flight;
import com.example.flightbookingservice.service.FlightService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/search")
    public ResponseEntity<ConsolidatedSearchResponse> searchFlightsWithDetails(
            @RequestParam String originCity,
            @RequestParam String destinationCity,
            @RequestParam(required = false, defaultValue = "USD") String toCurrency) {
        ConsolidatedSearchResponse response = flightService.searchFlightsWithDetails(originCity, destinationCity, toCurrency);
        return ResponseEntity.ok(response);
    }
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;

    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@RequestParam Long flightId, @RequestParam int numberOfSeats, @RequestParam String passengerName) {
        Optional<Booking> booking = flightService.bookFlight(flightId, numberOfSeats, passengerName);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.badRequest().body("Unable to book flight. Insufficient available seats or flight not found.");
        }
    }
}


