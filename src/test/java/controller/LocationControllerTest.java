package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.LocationController;
import com.sda.weather.controller.LocationMapper;
import com.sda.weather.service.LocationCreatorService;
import com.sda.weather.service.LocationProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LocationControllerTest {

    LocationController locationController;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        LocationCreatorService locationCreatorService = new LocationCreatorService(weatherRepositoryMock);
        LocationMapper locationMapper = new LocationMapper();
        LocationProviderService locationProviderService = new LocationProviderService(weatherRepositoryMock);
        locationController = new LocationController(locationCreatorService, locationProviderService, locationMapper);
    }

    @Test
    void whenAddsNewLocation_givenProperParameters_thenAddsNewLocation() throws JsonProcessingException {
        //when
        String s = locationController.addNewLocation("Poland", "Europe", "Gdansk", 32.3232, 43.4234);

        //then
        assertThat(s).isNotEmpty();
        assertThat(s).isEqualTo("{\"id\": 1, \"countryName\": Poland, \"regionName\": Europe, \"cityName\": Gdansk, \"latitude\": 32.3232, \"longitude\": 43.4234}");
    }

    @Test
    void whenAddsNewLocation_givenNullValuesForCountryOrCity_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationController.addNewLocation("", "Asia", "Hongkong", 53.32, 34.42));
        Throwable throwable1 = catchThrowable(() -> locationController.addNewLocation("Brazil", "South America", "", 32.32, 43.43));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
        assertThat(throwable1).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLatitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationController.addNewLocation("Denmark", "Europe", "Kopenhaga", 91.23, 32.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLongitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationController.addNewLocation("Denmark", "Europe", "Kopenhaga", 32.43, -181.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenGetsLocationsFromNotEmptyDatabase_thenReturnsLocationsMappedOnJSON() throws JsonProcessingException {
        //when
        locationController.addNewLocation("X", "Y", "Z", 32.32, 43.43);
        String locations = locationController.getLocations();

        //then
        assertThat(locations).isNotEmpty();
        assertThat(locations).isEqualTo("[{\"id\":1,\"countryName\":\"X\",\"regionName\":\"Y\",\"cityName\":\"Z\",\"latitude\":32.32,\"longitude\":43.43}]");
    }

    @Test
    void whenGetsLocationsFromEmptyDatabase_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationController.getLocations());

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

}
