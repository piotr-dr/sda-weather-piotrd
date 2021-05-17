package com.sda.weather.service.weatherforecastfactory.openweathermap;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenWeatherForecastDailyDTO {

    private String dt;
    private OpenWeatherForecastTempDTO temp;
    private String pressure;
    private String humidity;
    private String wind_speed;
    private String wind_deg;
}
