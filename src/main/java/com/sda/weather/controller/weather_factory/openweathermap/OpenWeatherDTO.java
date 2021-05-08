package com.sda.weather.controller.weather_factory.openweathermap;

import com.sda.weather.controller.weather_factory.WeatherDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenWeatherDTO implements WeatherDTO {

    private OpenWeatherMainDTO main;
    private OpenWeatherWindDTO wind;

    @Override
    public String getTemperature() {
        return main.getTemp();
    }

    @Override
    public String getPressure() {
        return main.getPressure();
    }

    @Override
    public String getHumidity() {
        return main.getHumidity();
    }

    @Override
    public String getWindDirection() {
        return wind.getDeg();
    }

    @Override
    public String getWindSpeed() {
        return wind.getSpeed();
    }
}
