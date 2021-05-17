package com.sda.weather.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.service.entities.WeatherInfo;
import com.sda.weather.service.WeatherInfoService;

import java.time.LocalDate;

public class WeatherInfoController {

    WeatherInfoService weatherInfoService;
    WeatherInfoMapper weatherInfoMapper;
    ObjectMapper objectMapper;

    public WeatherInfoController(WeatherInfoService weatherInfoService, WeatherInfoMapper weatherInfoMapper) {
        this.weatherInfoService = weatherInfoService;
        this.weatherInfoMapper = weatherInfoMapper;
        this.objectMapper = WeatherObjectMapper.getObjectMapper();
    }

    public String getWeatherInfo(Long locationId, LocalDate date) {
        WeatherInfo weatherInfo = weatherInfoService.getWeatherInfo(locationId, date);
        weatherInfoService.clearWeatherLists();
        WeatherInfoDTO weatherInfoDTO = weatherInfoMapper.asWeatherInfoDTO(weatherInfo);
        String response = null;
        try {
            response = objectMapper.writeValueAsString(weatherInfoDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
