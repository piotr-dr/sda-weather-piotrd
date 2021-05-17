package repository;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationDAOMock implements LocationDAO {

    private List<Location> dataBaseMock = new ArrayList<>();
    private Long index = 0L;

    @Override
    public Location saveLocation(Location location) {
        location.setId(++index);
        dataBaseMock.add(location);
        return location;
    }

    @Override
    public List<Location> getLocations() {
        return dataBaseMock;
    }

    @Override
    public Optional<Location> getLocation(Long locationId) {
        return Optional.ofNullable(dataBaseMock.get(locationId.intValue()));
    }
}
