package com.sda.weather.service;

import com.sda.weather.repository.WeatherRepositoryImpl;

import java.util.List;

public class GettingLocationService {

    WeatherRepositoryImpl weatherRepository;

    public GettingLocationService(WeatherRepositoryImpl weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<Location> getLocations() {
        List<Location> locations = weatherRepository.getLocations();
        return locations;
    }

}
