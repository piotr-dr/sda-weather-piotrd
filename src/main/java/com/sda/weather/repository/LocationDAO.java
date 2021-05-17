package com.sda.weather.repository;

import com.sda.weather.service.entities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationDAO {

    Location saveLocation(Location location);

    List<Location> getLocations();

    Optional<Location> getLocation(Long locationId);
}
