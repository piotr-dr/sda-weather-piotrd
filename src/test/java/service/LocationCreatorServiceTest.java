package service;

import com.sda.weather.service.LocationCreatorService;
import com.sda.weather.service.entities.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LocationDAOMock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LocationCreatorServiceTest {

    LocationCreatorService locationCreatorService;

    @BeforeEach
    void setUp() {
        LocationDAOMock weatherRepositoryMock = new LocationDAOMock();
        locationCreatorService = new LocationCreatorService(weatherRepositoryMock);
    }

    @Test
    void whenAddsNewLocation_givenProperParameters_thenAddsNewLocation() {
        //when
        Location newLocation = locationCreatorService.createNewLocation("Poland", "Europe", "Gdansk",32.3232, 43.4234);

        //then
        assertThat(newLocation.getId()).isNotNull();
        assertThat(newLocation.getCityName()).isEqualTo("Gdansk");
        assertThat(newLocation.getCountryName()).isEqualTo("Poland");
        assertThat(newLocation.getRegionName()).isEqualTo("Europe");
        assertThat(newLocation.getLatitude()).isEqualTo(32.3232);
        assertThat(newLocation.getLongitude()).isEqualTo(43.4234);
    }

    @Test
    void whenAddsNewLocation_givenNullValuesForCountryOrCity_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationCreatorService.createNewLocation("", "Asia", "Hongkong", 53.32, 34.42));
        Throwable throwable1 = catchThrowable(() -> locationCreatorService.createNewLocation("Brazil", "South America", "", 32.32, 43.43));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
        assertThat(throwable1).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLatitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationCreatorService.createNewLocation("Denmark", "Europe", "Kopenhaga", 91.23, 32.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void whenAddsNewLocation_givenLongitudeOutOfRange_thenThrowsAnException() {
        //when
        Throwable throwable = catchThrowable(() -> locationCreatorService.createNewLocation("Denmark", "Europe", "Kopenhaga", 32.43, -181.32));

        //then
        assertThat(throwable).isExactlyInstanceOf(RuntimeException.class);
    }
}
