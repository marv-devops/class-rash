package com.example.flightbookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ConsolidatedSearchResponse {
    private String originCity;
    private String destinationCity;
    private MapQuestResponse drivingDetails; // Ensure this type matches what you intend to store
    private List<FlickrImage> images;
    private List<FlightSearchResponse> flights; // Use FlightSearchResponse if it contains additional details

    // You might only need one constructor here, make sure to match the fields with the parameters
    public ConsolidatedSearchResponse(String originCity, String destinationCity, MapQuestResponse drivingDetails, List<FlickrImage> images, List<FlightSearchResponse> flightResponses) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.drivingDetails = drivingDetails; // Assuming you have a method to convert or extract DrivingDetails
        this.images = images;
        this.flights = flightResponses;
    }

    // Utility method to convert or extract DrivingDetails from MapQuestResponse (implement this according to your actual structure)
    private DrivingDetails convertMapQuestResponseToDrivingDetails(MapQuestResponse response) {
        // Implement conversion logic here
        return new DrivingDetails(); // Placeholder return, adjust according to your actual logic
    }

    // Depending on your implementation, you might not need the utility method if DrivingDetails directly maps from MapQuestResponse
}
