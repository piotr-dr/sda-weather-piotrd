package com.sda.weather.service.currentweatherfactory;

public interface WeatherDTO {
    String getTemperature();

    String getPressure();

    String getHumidity();

    String getWindDirection();

    String getWindSpeed();
}
