package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.service.entities.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {

    public LocationDTO asLocationDTO(Location location) throws JsonProcessingException {
        LocationDTO locationDTO = new LocationDTO()
                .setId(location.getId())
                .setCityName(location.getCityName())
                .setCountryName(location.getCountryName())
                .setLatitude(location.getLatitude())
                .setLongitude(location.getLongitude())
                .build();
        return locationDTO;
    }

    public List<LocationDTO> asLocationDTOList(List<Location> locations) {
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
