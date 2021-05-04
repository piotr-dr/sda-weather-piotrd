package com.sda.weather.controller.weather_factory.openweathermap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.controller.weather_factory.WeatherDTO;
import com.sda.weather.controller.weather_factory.WeatherDTOFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class OpenWeatherDTOFactory implements WeatherDTOFactory {

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {

        OpenWeatherDTO openWeatherDTO;
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                            "&appid=abc8c6e2e5d31d8874b98f270036015a&units=metric"))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = httpResponse.body();

            openWeatherDTO = WeatherObjectMapper.getObjectMapper().readValue(bodyResponse, OpenWeatherDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }

        return openWeatherDTO;
    }
}
