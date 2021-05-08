package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class GettingLocationController { // todo move to LocationController

    private GettingLocationService gettingLocationService;

    public GettingLocationController(GettingLocationService gettingLocationService) {
        this.gettingLocationService = gettingLocationService;
    }

    public String getLocations() throws JsonProcessingException {
        List<LocationDTO> locationDTOList = getLocationDTOList();
        String locationsJSON = WeatherObjectMapper.getObjectMapper().writeValueAsString(locationDTOList);
        return locationsJSON;
    }

    private List<LocationDTO> getLocationDTOList() {
        List<Location> locations = gettingLocationService.getLocations();
        List<LocationDTO> locationDTOList = new ArrayList<>();
        for (Location location : locations) { // todo move it to LocationMapper
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
