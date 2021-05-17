package com.sda.weather.service.currentweatherfactory.weatherbit;

import com.sda.weather.service.currentweatherfactory.WeatherDTO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WeatherbitDTO implements WeatherDTO {

    private WeatherbitDataDTO[] data;

    @Override
    public String getTemperature() {
        return data[0].getTemp();
    }

    @Override
    public String getPressure() {
        return data[0].getPres();
    }

    @Override
    public String getHumidity() {
        return data[0].getRh();
    }

    @Override
    public String getWindDirection() {
        return data[0].getWind_dir();
    }

    @Override
    public String getWindSpeed() {
        return data[0].getWind_spd();
    }
}
