package repository;

import com.sda.weather.repository.LocationDAO;
import com.sda.weather.service.entities.Location;

import java.util.ArrayList;
import java.util.List;

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
    public Location getLocation(String cityName, String countryName) {
        return null;
    }
}
