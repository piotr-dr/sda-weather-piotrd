package com.sda.weather.service;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;

public class AddingLocationService {

    private LocationDAO locationDAO;
    private final double MIN_LATITUDE_VALUE = -90;
    private final double MAX_LATITUDE_VALUE = 90;
    private final double MIN_LONGITUDE_VALUE = -180;
    private final double MAX_LONGITUDE_VALUE = 180;

    public AddingLocationService(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Location createNewLocation(String country, String region, String city, double latitude, double longitude) {
        if(country.isEmpty()) {
            throw new RuntimeException("Country's name can't be null.");
        }
        if(city.isEmpty()) {
            throw new RuntimeException("City's name can't be null");
        }
        if(latitude<MIN_LATITUDE_VALUE || latitude>MAX_LATITUDE_VALUE) {
            throw new RuntimeException("Latitude's out of range.");
        }
        if(longitude<MIN_LONGITUDE_VALUE || longitude>MAX_LONGITUDE_VALUE) {
            throw new RuntimeException("Longitude's out of range.");
        }

        Location location = new Location(country, region, city, latitude, longitude);
        Location savedLocation = locationDAO.saveLocation(location);
        return savedLocation;
    }

}
