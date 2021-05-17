package com.sda.weather.service.currentweatherfactory.weatherstack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class WeatherStackCurrentDTO {

    private String temperature;
    private String wind_speed;
    private String wind_degree;
    private String pressure;
    private String humidity;

}
