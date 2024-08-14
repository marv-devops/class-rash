package com.example.flightbookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originCity;
    private String destinationCity;
    private String airline;
    private int availableSeats;
    private int numberOfConnections;
    private double ticketPrice;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    // Constructors, getters, and setters

    public Flight(String originCity, String destinationCity, String airline, int availableSeats, int numberOfConnections, double ticketPrice) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.airline = airline;
        this.availableSeats = availableSeats;
        this.numberOfConnections = numberOfConnections;
        this.ticketPrice = ticketPrice;
        // Initialize the bookings set
        this.bookings = new HashSet<>();
    }

    public Flight() {

    }

    // Assume constructors, getters, and setters for all fields are here, including the bookings set

    public boolean bookSeats(int numberOfSeats, String passengerName) {
        if (this.availableSeats >= numberOfSeats) {
            this.availableSeats -= numberOfSeats;
            Booking booking = new Booking(passengerName, numberOfSeats, this);
            this.bookings.add(booking);
            return true;
        }
        return false;
    }

    // Getters and Setters for the bookings field
    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
