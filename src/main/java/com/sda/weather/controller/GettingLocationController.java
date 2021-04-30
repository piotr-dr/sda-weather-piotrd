package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.Location;

import java.util.ArrayList;
import java.util.List;

public class GettingLocationController {

    private GettingLocationService gettingLocationService;

    public GettingLocationController(GettingLocationService gettingLocationService) {
        this.gettingLocationService = gettingLocationService;
    }

    public String getLocations() throws JsonProcessingException {
        List<LocationDTO> locationDTOList = getLocationDTOList();
        if(locationDTOList.isEmpty()) {
            throw new RuntimeException("Getting location's list is empty.");
        }
        ObjectMapper objectMapper = WeatherObjectMapper.getObjectMapper();
        String locationsJSON = objectMapper.writeValueAsString(locationDTOList);
        return locationsJSON;
    }

    private List<LocationDTO> getLocationDTOList() {
        List<Location> locations = gettingLocationService.getLocations();
        List<LocationDTO> locationDTOList = new ArrayList<>();
        for (Location location : locations) {
            locationDTOList.add(new LocationDTO()
                    .setId(location.getId())
                    .setCountryName(location.getCountryName())
                    .setRegionName(location.getRegionName())
                    .setCityName(location.getCityName())
                    .setLatitude(location.getLatitude())
                    .setLongitude(location.getLongitude())
                    .build());
        }
        return locationDTOList;
    }

}
