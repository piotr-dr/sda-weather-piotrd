package com.sda.weather;

public class AddingLocationService {

    private WeatherRepositoryImpl weatherRepository;

    public AddingLocationService(WeatherRepositoryImpl weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Location createNewLocation(String country, String region, String city, double latitude, double longitude) {
        Location location = new Location(null, country, region, city, latitude, longitude);
        Location savedLocation = weatherRepository.saveLocation(location);
        return savedLocation;
    }

}
