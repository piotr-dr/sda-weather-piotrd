package com.sda.weather.repository;

import com.sda.weather.service.entities.WeatherInfo;

public interface WeatherInfoDAO {

    WeatherInfo saveWeatherInfo(WeatherInfo weatherInfo);

}
