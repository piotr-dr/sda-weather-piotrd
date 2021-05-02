package repository;

import com.sda.weather.repository.WeatherRepository;
import com.sda.weather.service.Location;

import java.util.ArrayList;
import java.util.List;

public class WeatherRepositoryMock implements WeatherRepository {

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
}
