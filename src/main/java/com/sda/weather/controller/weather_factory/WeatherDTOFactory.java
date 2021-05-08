package com.sda.weather.controller.weather_factory;

import com.sda.weather.controller.weather_factory.weatherstack.WeatherStackDTO;

import java.time.LocalDate;

public interface WeatherDTOFactory {

    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date);

}
