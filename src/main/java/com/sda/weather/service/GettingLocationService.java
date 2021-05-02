package com.sda.weather.service;

import com.sda.weather.repository.WeatherRepository;

import java.util.List;

public class GettingLocationService {

    WeatherRepository weatherRepository;

    public GettingLocationService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<Location> getLocations() {
        List<Location> locations = weatherRepository.getLocations();
        if (locations.isEmpty()) {
            throw new RuntimeException("Location's list is empty.");
        }
        return locations;
    }

}
