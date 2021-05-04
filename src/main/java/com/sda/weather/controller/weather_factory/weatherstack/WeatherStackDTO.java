package com.sda.weather.controller.weather_factory.weatherstack;

import com.sda.weather.controller.weather_factory.WeatherDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherStackDTO implements WeatherDTO{

    private WeatherStackCurrentDTO current;

    @Override
    public String getTemperature() {
        return current.getTemperature();
    }

    @Override
    public String getPressure() {
        return current.getPressure();
    }

    @Override
    public String getHumidity() {
        return current.getHumidity();
    }

    @Override
    public String getWindDirection() {
        return current.getWind_degree();
    }

    @Override
    public String getWindSpeed() {
        return current.getWind_speed();
    }
}
