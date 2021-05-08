package com.sda.weather.controller.weather_factory;

import java.time.LocalDate;

public interface WeatherDTOFactory {
    WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date);
}
