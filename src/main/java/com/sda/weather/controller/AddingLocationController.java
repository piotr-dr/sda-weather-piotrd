package com.sda.weather.controller;

import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.entities.Location;

public class AddingLocationController {

    private AddingLocationService addingLocationService;

    public AddingLocationController(AddingLocationService addingLocationService) {
        this.addingLocationService = addingLocationService;
    }

    public String addNewLocation(String country, String region, String city, double latitude, double longitude) {
        Location newLocation = addingLocationService.createNewLocation(country, region, city, latitude, longitude);
        return String.format("[{\"id\":%s,\"countryName\":\"%s\",\"regionName\":\"%s\"," +
                        "\"cityName\":\"%s\",\"latitude\":%s,\"longitude\":%s]}",
                newLocation.getId(), newLocation.getCountryName(), newLocation.getRegionName(),
                newLocation.getCityName(), newLocation.getLatitude(), newLocation.getLongitude());
    }

}
