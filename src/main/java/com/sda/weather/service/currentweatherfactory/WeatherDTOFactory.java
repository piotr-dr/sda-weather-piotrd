package com.sda.weather.service.currentweatherfactory;

import java.time.LocalDate;

public interface WeatherDTOFactory {
    WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date);
}
