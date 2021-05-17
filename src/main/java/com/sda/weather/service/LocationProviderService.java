package com.sda.weather.service;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationProviderService {

    private final LocationDAO locationDAO;

    public List<Location> getLocations() {
        return locationDAO.getLocations();
    }

    public Optional<Location> getLocation(Long locationId) {
        return locationDAO.getLocation(locationId);
    }

}
