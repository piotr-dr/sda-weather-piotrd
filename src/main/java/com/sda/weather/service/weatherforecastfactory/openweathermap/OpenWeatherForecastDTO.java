package com.sda.weather.service.weatherforecastfactory.openweathermap;

import com.sda.weather.service.weatherforecastfactory.WeatherForecastDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OpenWeatherForecastDTO implements WeatherForecastDTO {

    private OpenWeatherForecastDailyDTO[] daily;

    @Override
    public String getDate(int dayNumber) {
        return daily[dayNumber].getDt();
    }

    @Override
    public String getTemperature(int dayNumber) {
        return daily[dayNumber].getTemp().getDay();
    }

    @Override
    public String getPressure(int dayNumber) {
        return daily[dayNumber].getPressure();
    }

    @Override
    public String getHumidity(int dayNumber) {
        return daily[dayNumber].getHumidity();
    }

    @Override
    public String getWindDirection(int dayNumber) {
        return daily[dayNumber].getWind_deg();
    }

    @Override
    public String getWindSpeed(int dayNumber) {
        return daily[dayNumber].getWind_speed();
    }
}
