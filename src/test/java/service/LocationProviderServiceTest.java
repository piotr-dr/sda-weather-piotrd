package service;

import com.sda.weather.service.LocationCreatorService;
import com.sda.weather.service.LocationProviderService;
import com.sda.weather.service.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;

public class LocationProviderServiceTest {

    LocationProviderService locationProviderService;
    LocationCreatorService locationCreatorService;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        locationProviderService = new LocationProviderService(weatherRepositoryMock);
        locationCreatorService = new LocationCreatorService(weatherRepositoryMock);
    }

    @Test
    void whenGetsLocationsFromNotEmptyDatabase_thenGetsAllLocations() {
        //when
        Location newLocation = locationCreatorService.createNewLocation("X", "X", "X", 32.32, 32.32);
        Location newLocation1 = locationCreatorService.createNewLocation("X", "X", "X", 32.32, 32.32);
        List<Location> locations = locationProviderService.getLocations();

        //then
        assertThat(locations).isNotEmpty();
        assertThat(locations).containsOnly(newLocation, newLocation1);
    }

    @Test
    void whenGetsLocationsFromEmptyDatabase_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationProviderService.getLocations());

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

}
