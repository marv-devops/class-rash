package com.example.flightbookingservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlickrResponse {
    private Photos photos;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Photos {
        private List<Photo> photo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Photo {
        private String id;
        private String secret;
        private String server;
        private int farm;
        private String title;

        // This does not need @JsonProperty since it's a method, not a field
        public String getImageUrl() {
            return String.format("https://farm%d.staticflickr.com/%s/%s_%s.jpg", farm, server, id, secret);
        }
    }
}
