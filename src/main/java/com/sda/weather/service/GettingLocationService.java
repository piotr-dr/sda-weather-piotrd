package com.sda.weather.service;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;
import lombok.RequiredArgsConstructor;

import java.util.List;

// todo @RequiredArgsConstructor
public class GettingLocationService { // todo rename to LocationProviderService

    LocationDAO locationDAO; // todo mark as a private final

    public GettingLocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public List<Location> getLocations() {
        List<Location> locations = locationDAO.getLocations();
        if (locations.isEmpty()) {
            System.out.println("Your location list is empty.");
        }
        return locations;
    }

}
