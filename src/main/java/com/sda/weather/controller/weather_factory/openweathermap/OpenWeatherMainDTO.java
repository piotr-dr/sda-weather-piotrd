package com.sda.weather.controller.weather_factory.openweathermap;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenWeatherMainDTO {

    private String temp;
    private String pressure;
    private String humidity;

}
