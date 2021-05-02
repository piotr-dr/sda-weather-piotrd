package com.sda.weather.controller;

public class LocationDTO {

    private Long id;
    private String countryName;
    private String regionName;
    private String cityName;
    private double latitude;
    private double longitude;

    public LocationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LocationDTO setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public LocationDTO setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public LocationDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public LocationDTO setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationDTO setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationDTO build() {
        return this;
    }
}
