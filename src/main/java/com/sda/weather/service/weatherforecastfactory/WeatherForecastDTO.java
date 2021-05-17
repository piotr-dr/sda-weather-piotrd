package com.sda.weather.service.weatherforecastfactory;

public interface WeatherForecastDTO {

    String getDate(int dayNumber);

    String getTemperature(int dayNumber);

    String getPressure(int dayNumber);

    String getHumidity(int dayNumber);

    String getWindDirection(int dayNumber);

    String getWindSpeed(int dayNumber);

}
