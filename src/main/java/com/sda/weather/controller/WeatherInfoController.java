package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.service.entities.WeatherInfo;
import com.sda.weather.service.WeatherInfoService;

import java.time.LocalDate;

public class WeatherInfoController {

    WeatherInfoService weatherInfoService;

    public WeatherInfoController(WeatherInfoService weatherInfoService) {
        this.weatherInfoService = weatherInfoService;
    }

    public String getWeatherInfo(String cityName, String countryName, LocalDate date) throws JsonProcessingException {
        WeatherInfoDTO weatherInfoDTO = createWeatherInfoDTO(cityName, countryName, date);
        String response = WeatherObjectMapper.getObjectMapper().writeValueAsString(weatherInfoDTO);
        return response;
    }

    private WeatherInfoDTO createWeatherInfoDTO(String cityName, String countryName, LocalDate date) {
        WeatherInfo weatherInfo = weatherInfoService.getWeatherInfo(cityName, countryName, date);
        WeatherInfoDTO weatherInfoDTO = new WeatherInfoDTO()
                .setId(weatherInfo.getId())
                .setTemperature(weatherInfo.getTemperature())
                .setPressure(weatherInfo.getPressure())
                .setHumidity(weatherInfo.getHumidity())
                .setWindDirection(weatherInfo.getWindDirection())
                .setWindSpeed(weatherInfo.getWindSpeed());
        return weatherInfoDTO;
    }
}
