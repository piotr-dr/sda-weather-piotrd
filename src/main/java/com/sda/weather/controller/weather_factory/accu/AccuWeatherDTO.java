package com.sda.weather.controller.weather_factory.accu;

import com.sda.weather.controller.weather_factory.WeatherDTO;
import lombok.Setter;

public class AccuWeatherDTO implements WeatherDTO {

    @Setter
    private String temperature;
    private String airPressure;
    private String humidity;
    private String windDirection;
    private String windSpeed;

    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public String getPressure() {
        return airPressure;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    @Override
    public String getWindDirection() {
        return windDirection;
    }

    @Override
    public String getWindSpeed() {
        return windSpeed;
    }

    @Override
    public String toString() {
        return "AccuWeatherDTO{" +
                "temperature='" + temperature + '\'' +
                ", airPressure='" + airPressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                '}';
    }
}
