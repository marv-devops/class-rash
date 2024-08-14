package com.example.flightbookingservice.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MapQuestResponse {
    private Route route;

    // Getter and Setter
    // ...
@Getter
@Setter
    public static class Route {
        private String formattedTime;
        private double distance;
        private boolean hasHighway;
        private boolean hasCountryCross;

        // Getter and Setter
        // ...
    }
}
