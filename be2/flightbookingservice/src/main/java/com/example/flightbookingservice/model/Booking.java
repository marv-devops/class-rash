package com.example.flightbookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String passengerName;
    private int numberOfSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    // Constructors, getters, and setters
    public Booking() {
    }

    public Booking(String passengerName, int numberOfSeats, Flight flight) {
        this.passengerName = passengerName;
        this.numberOfSeats = numberOfSeats;
        this.flight = flight;
    }

    // Assume getters and setters for all fields are here
}

