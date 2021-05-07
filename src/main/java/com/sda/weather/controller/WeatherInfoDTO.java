package com.sda.weather.controller;

public class WeatherInfoDTO {

    private Long id;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windDirection;
    private Double windSpeed;

    public WeatherInfoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public WeatherInfoDTO setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public WeatherInfoDTO setPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public WeatherInfoDTO setHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public WeatherInfoDTO setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public WeatherInfoDTO setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public WeatherInfoDTO build() {
        return this;
    }
}
