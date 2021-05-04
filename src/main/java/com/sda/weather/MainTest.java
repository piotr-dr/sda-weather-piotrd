package com.sda.weather;

import com.sda.weather.controller.weather_factory.WeatherDTO;
import com.sda.weather.controller.weather_factory.openweathermap.OpenWeatherDTOFactory;
import com.sda.weather.controller.weather_factory.weatherbit.WeatherBitDTOFactory;
import com.sda.weather.controller.weather_factory.weatherstack.WeatherStackDTO;
import com.sda.weather.controller.weather_factory.weatherstack.WeatherStackDTOFactory;

import java.time.LocalDate;

public class MainTest {

    public static void main(String[] args) {

        WeatherBitDTOFactory weatherBitDTOFactory = new WeatherBitDTOFactory();
        WeatherDTO weatherDTO = weatherBitDTOFactory.downloadWeather("Warsaw", "Poland", LocalDate.now());

        System.out.println(weatherDTO);

        /*OpenWeatherDTOFactory openWeatherDTOFactory = new OpenWeatherDTOFactory();
        WeatherDTO weatherDTO1 = openWeatherDTOFactory.downloadWeather("Warsaw", "Poland", LocalDate.now());

        System.out.println(weatherDTO1);*/

    }

}
