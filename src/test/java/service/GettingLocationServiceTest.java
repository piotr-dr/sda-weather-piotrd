package service;

import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;

public class GettingLocationServiceTest {

    GettingLocationService gettingLocationService;
    AddingLocationService addingLocationService;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        gettingLocationService = new GettingLocationService(weatherRepositoryMock);
        addingLocationService = new AddingLocationService(weatherRepositoryMock);
    }

    @Test
    void whenGetsLocationsFromNotEmptyDatabase_thenGetsAllLocations() {
        //when
        Location newLocation = addingLocationService.createNewLocation("X", "X", "X", 32.32, 32.32);
        Location newLocation1 = addingLocationService.createNewLocation("X", "X", "X", 32.32, 32.32);
        List<Location> locations = gettingLocationService.getLocations();

        //then
        assertThat(locations).isNotEmpty();
        assertThat(locations).containsOnly(newLocation, newLocation1);
    }

    @Test
    void whenGetsLocationsFromEmptyDatabase_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> gettingLocationService.getLocations());

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

}
