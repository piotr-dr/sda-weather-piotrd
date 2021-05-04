package com.sda.weather.controller;

import com.sda.weather.service.WeatherInfoService;

import java.time.LocalDate;

public class WeatherInfoController {

    WeatherInfoService weatherInfoService;

    public WeatherInfoController(WeatherInfoService weatherInfoService) {
        this.weatherInfoService = weatherInfoService;
    }

    public String getWeatherInfo(String cityName, String countryName, LocalDate date) {
        String weatherInfo = weatherInfoService.getWeatherInfo(cityName, countryName, date);
        return weatherInfo;
    }
}
