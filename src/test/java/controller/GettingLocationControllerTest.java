package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.GettingLocationController;
import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class GettingLocationControllerTest {

    GettingLocationController gettingLocationController;
    AddingLocationService addingLocationService;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        addingLocationService = new AddingLocationService(weatherRepositoryMock);
        GettingLocationService gettingLocationService = new GettingLocationService(weatherRepositoryMock);
        gettingLocationController = new GettingLocationController(gettingLocationService);
    }

    @Test
    void whenGetsLocationsFromNotEmptyDatabase_thenReturnsLocationsMappedOnJSON() throws JsonProcessingException {
        //when
        Location newLocation = addingLocationService.createNewLocation("X", "Y", "Z", 32.32, 43.43);
        String locations = gettingLocationController.getLocations();

        //then
        assertThat(locations).isNotEmpty();
        assertThat(locations).isEqualTo("[{\"id\":1,\"countryName\":\"X\",\"regionName\":\"Y\",\"cityName\":\"Z\",\"latitude\":32.32,\"longitude\":43.43}]");
    }

    @Test
    void whenGetsLocationsFromEmptyDatabase_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> gettingLocationController.getLocations());

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }
}
