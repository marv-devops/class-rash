package com.example.flightbookingservice.repository;

import com.example.flightbookingservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginCityAndDestinationCity(String originCity, String destinationCity);
}

