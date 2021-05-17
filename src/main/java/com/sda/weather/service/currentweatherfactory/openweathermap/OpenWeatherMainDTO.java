package com.sda.weather.service.currentweatherfactory.openweathermap;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenWeatherMainDTO {

    private String temp;
    private String pressure;
    private String humidity;

}
