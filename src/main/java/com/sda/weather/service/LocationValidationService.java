package com.sda.weather.service;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;

public class LocationValidationService {

    private LocationDAO locationDAO;

    public LocationValidationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location isAdded (String cityName, String countryName) {
        return locationDAO.getLocation(cityName, countryName);
    }

}
