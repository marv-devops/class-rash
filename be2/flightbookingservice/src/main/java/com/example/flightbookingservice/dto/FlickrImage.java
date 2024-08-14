package com.example.flightbookingservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FlickrImage {
    private String id;
    private String title;
    private String imageUrl;

    // Constructors, getters, and setters
    public FlickrImage(String id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    // Getters and setters...
}

