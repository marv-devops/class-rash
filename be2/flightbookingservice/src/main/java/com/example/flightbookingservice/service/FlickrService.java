package com.example.flightbookingservice.service;

import com.example.flightbookingservice.dto.FlickrImage;
import com.example.flightbookingservice.dto.FlickrResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlickrService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public FlickrService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<FlickrImage> getImagesForCity(String city) {
        String apiKey = "1c676f8ed8e127381341e7a550d34dd2";
        String url = String.format("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=%s&text=%scity&per_page=10&page=1&format=json&nojsoncallback=1", apiKey, city);

        List<FlickrImage> images = new ArrayList<>();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            FlickrResponse flickrResponse = objectMapper.readValue(response.getBody(), FlickrResponse.class);
            for (FlickrResponse.Photo photo : flickrResponse.getPhotos().getPhoto()) {
                images.add(new FlickrImage(photo.getId(), photo.getTitle(), photo.getImageUrl()));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Consider a better error handling strategy
        }

        return images;
    }
}
