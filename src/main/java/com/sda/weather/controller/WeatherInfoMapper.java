package com.sda.weather.controller;

import com.sda.weather.service.entities.WeatherInfo;

public class WeatherInfoMapper {

    public WeatherInfoDTO asWeatherInfoDTO(WeatherInfo weatherInfo) {
        WeatherInfoDTO weatherInfoDTO = new WeatherInfoDTO()
                .setId(weatherInfo.getId())
                .setTemperature(weatherInfo.getTemperature())
                .setPressure(weatherInfo.getPressure())
                .setHumidity(weatherInfo.getHumidity())
                .setWindDirection(weatherInfo.getWindDirection())
                .setWindSpeed(weatherInfo.getWindSpeed())
                .build();
        return weatherInfoDTO;
    }

}
