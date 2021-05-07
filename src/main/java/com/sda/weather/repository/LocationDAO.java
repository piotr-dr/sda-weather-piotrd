package com.sda.weather.repository;

import com.sda.weather.service.entities.Location;

import java.util.List;

public interface LocationDAO {

    Location saveLocation(Location location);

    List<Location> getLocations();

    Location getLocation(String cityName, String countryName);
}
