package com.example.flightbookingservice.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConversionService {

    public Double getConversionRate(String fromCurrency, String toCurrency) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://host.docker.internal:8080/exchangeRate?fromCur=" + fromCurrency + "&toCur=" + toCurrency;
        Double rate = restTemplate.getForObject(url, Double.class);
        return rate != null ? rate : 0.0;
    }
}
