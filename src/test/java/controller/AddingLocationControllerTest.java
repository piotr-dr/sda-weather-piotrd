package controller;

import com.sda.weather.controller.AddingLocationController;
import com.sda.weather.service.AddingLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AddingLocationControllerTest {

    AddingLocationController addingLocationController;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        AddingLocationService addingLocationService = new AddingLocationService(weatherRepositoryMock);
        addingLocationController = new AddingLocationController(addingLocationService);
    }

    @Test
    void whenAddsNewLocation_givenProperParameters_thenAddsNewLocation() {
        //when
        String s = addingLocationController.addNewLocation("Poland", "Europe", "Gdansk", 32.3232, 43.4234);

        //then
        assertThat(s).isNotEmpty();
        assertThat(s).isEqualTo("{\"id\": 1, \"countryName\": Poland, \"regionName\": Europe, \"cityName\": Gdansk, \"latitude\": 32.3232, \"longitude\": 43.4234}");
    }

    @Test
    void whenAddsNewLocation_givenNullValuesForCountryOrCity_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> addingLocationController.addNewLocation("", "Asia", "Hongkong", 53.32, 34.42));
        Throwable throwable1 = catchThrowable(() -> addingLocationController.addNewLocation("Brazil", "South America", "", 32.32, 43.43));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
        assertThat(throwable1).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLatitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> addingLocationController.addNewLocation("Denmark", "Europe", "Kopenhaga", 91.23, 32.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLongitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> addingLocationController.addNewLocation("Denmark", "Europe", "Kopenhaga", 32.43, -181.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

}
