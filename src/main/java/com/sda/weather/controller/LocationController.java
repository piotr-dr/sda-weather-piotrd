package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.service.LocationCreatorService;
import com.sda.weather.service.LocationProviderService;
import com.sda.weather.service.entities.Location;

import java.util.List;

public class LocationController {

    private LocationCreatorService locationCreatorService;
    private LocationProviderService locationProviderService;
    private LocationMapper locationMapper;
    private ObjectMapper objectMapper;

    public LocationController(LocationCreatorService locationCreatorService, LocationProviderService locationProviderService,
                              LocationMapper locationMapper) {
        this.locationCreatorService = locationCreatorService;
        this.locationProviderService = locationProviderService;
        this.locationMapper = locationMapper;
        this.objectMapper = WeatherObjectMapper.getObjectMapper();
    }

    public String addNewLocation(String country, String region, String city, double latitude, double longitude) {
        Location newLocation = locationCreatorService.createNewLocation(country, region, city, latitude, longitude);
        LocationDTO locationDTO = null;
        try {
            locationDTO = locationMapper.asLocationDTO(newLocation);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String response = null;
        try {
            response = objectMapper.writeValueAsString(locationDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getLocations() {
        List<Location> locations = locationProviderService.getLocations();
        List<LocationDTO> locationDTOList = locationMapper.asLocationDTOList(locations);
        String response = null;
        try {
            response = objectMapper.writeValueAsString(locationDTOList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

}
