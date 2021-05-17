package com.sda.weather.service;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;

public class LocationCreatorService {

    private LocationDAO locationDAO;
    private static final double MIN_LATITUDE_VALUE = -90;
    private static final double MAX_LATITUDE_VALUE = 90;
    private static final double MIN_LONGITUDE_VALUE = -180;
    private static final double MAX_LONGITUDE_VALUE = 180;

    public LocationCreatorService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location createNewLocation(String country, String region, String city, double latitude, double longitude) {
        if (country.isBlank() || country == null) {
            throw new RuntimeException("Country's name can't be null.");
        }
        if (city.isBlank() || city == null) {
            throw new RuntimeException("City's name can't be null");
        }
        if (latitude < MIN_LATITUDE_VALUE || latitude > MAX_LATITUDE_VALUE) {
            throw new RuntimeException("Latitude's out of range.");
        }
        if (longitude < MIN_LONGITUDE_VALUE || longitude > MAX_LONGITUDE_VALUE) {
            throw new RuntimeException("Longitude's out of range.");
        }
        if (region.isBlank()) {
            region = null;
        }

        Location location = new Location(country, region, city, latitude, longitude);
        return locationDAO.saveLocation(location);
    }

}
