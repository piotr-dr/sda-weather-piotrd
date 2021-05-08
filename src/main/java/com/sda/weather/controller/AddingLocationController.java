package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.entities.Location;

public class AddingLocationController {

    private AddingLocationService addingLocationService;

    public AddingLocationController(AddingLocationService addingLocationService) {
        this.addingLocationService = addingLocationService;
    }

    public String addNewLocation(String country, String region, String city, double latitude, double longitude) throws JsonProcessingException {
        Location newLocation = addingLocationService.createNewLocation(country, region, city, latitude, longitude);
        LocationDTO locationDTO = new LocationDTO()
                .setId(newLocation.getId())
                .setCountryName(newLocation.getCountryName())
                .setCityName(newLocation.getCityName())
                .setRegionName(newLocation.getRegionName())
                .setLatitude(newLocation.getLatitude())
                .setLongitude(newLocation.getLongitude())
                .build();
        String response = WeatherObjectMapper.getObjectMapper().writeValueAsString(locationDTO);
        return response;
    }

}
