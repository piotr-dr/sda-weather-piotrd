package com.sda.weather.service.currentweatherfactory.weatherbit;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherbitDataDTO {

    private String rh;
    private String pres;
    private String wind_spd;
    private String wind_dir;
    private String temp;

}
