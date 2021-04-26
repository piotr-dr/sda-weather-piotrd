package com.sda.weather.repository;

import com.sda.weather.service.Location;

public interface WeatherRepository {

    Location saveLocation(Location location);

}
