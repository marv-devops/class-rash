package com.example.flightbookingservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.flightbookingservice.model.Flight;
import com.example.flightbookingservice.repository.FlightRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(FlightRepository repository) {
        return args -> {
            // Add flights for New York to Los Angeles
            repository.save(new Flight("New York", "Los Angeles", "Airline A", 100, 0, 199.99));
            repository.save(new Flight("New York", "Los Angeles", "Airline B", 90, 1, 299.99));
            repository.save(new Flight("New York", "Los Angeles", "Airline C", 80, 2, 399.99));
            repository.save(new Flight("New York", "Los Angeles", "Airline D", 70, 1, 299.99));
            repository.save(new Flight("New York", "Los Angeles", "Airline E", 60, 0, 199.99));

            // Add flights for Boston to San Francisco
            repository.save(new Flight("Boston", "San Francisco", "Airline F", 80, 0, 299.99));
            repository.save(new Flight("Boston", "San Francisco", "Airline G", 70, 1, 399.99));
            repository.save(new Flight("Boston", "San Francisco", "Airline H", 60, 2, 499.99));
            repository.save(new Flight("Boston", "San Francisco", "Airline I", 50, 1, 399.99));
            repository.save(new Flight("Boston", "San Francisco", "Airline J", 40, 0, 299.99));

            // Add completely different flights
            repository.save(new Flight("Chicago", "Miami", "Airline K", 120, 0, 249.99));
            repository.save(new Flight("Los Angeles", "Las Vegas", "Airline L", 80, 1, 199.99));
            repository.save(new Flight("Seattle", "Denver", "Airline M", 90, 0, 299.99));
            repository.save(new Flight("Houston", "New Orleans", "Airline N", 70, 1, 199.99));
            repository.save(new Flight("Atlanta", "Washington D.C.", "Airline O", 110, 0, 349.99));
            repository.save(new Flight("Dallas", "Phoenix", "Airline P", 60, 1, 249.99));
            repository.save(new Flight("Orlando", "New York", "Airline Q", 100, 0, 299.99));
            repository.save(new Flight("San Diego", "Portland", "Airline R", 50, 2, 399.99));
            repository.save(new Flight("Detroit", "Minneapolis", "Airline S", 40, 0, 199.99));
            repository.save(new Flight("Salt Lake City", "Kansas City", "Airline T", 30, 1, 299.99));
            repository.save(new Flight("Philadelphia", "Charlotte", "Airline U", 110, 0, 249.99));
            repository.save(new Flight("Indianapolis", "Cincinnati", "Airline V", 80, 1, 199.99));
            repository.save(new Flight("St. Louis", "Nashville", "Airline W", 90, 0, 349.99));
            repository.save(new Flight("Memphis", "Louisville", "Airline X", 70, 2, 299.99));
            repository.save(new Flight("Jacksonville", "Tampa", "Airline Y", 60, 0, 249.99));
        };
    }
}
