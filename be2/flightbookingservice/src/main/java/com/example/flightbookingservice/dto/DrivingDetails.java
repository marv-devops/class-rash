package com.example.flightbookingservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DrivingDetails {
    private String formattedTime;
    private double distance;
    private boolean hasHighway;
    private boolean hasCountryCross;

    // Constructors, Getters, and Setters
}

