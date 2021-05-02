package com.sda.weather.repository;

import com.sda.weather.service.Location;

import java.util.List;

public interface WeatherRepository {

    Location saveLocation(Location location);

    List<Location> getLocations();
}
