package com.sda.weather.service.weatherforecastfactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.service.weatherforecastfactory.openweathermap.OpenWeatherForecastDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class WeatherForecastDTOFactory {

    private String openWeatherAK;
    private ObjectMapper objectMapper;

    public WeatherForecastDTOFactory() {
        this.openWeatherAK = "abc8c6e2e5d31d8874b98f270036015a";
        this.objectMapper = WeatherObjectMapper.getObjectMapper();
    }

    public WeatherForecastDTO getFromOpenWeatherMap(double latitude, double longitude, LocalDate date) {
        OpenWeatherForecastDTO openWeatherForecastDTO;
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude +
                            "&exclude=current,minutely,hourly,alerts&appid=" + openWeatherAK + "&units=metric"))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = httpResponse.body();
            openWeatherForecastDTO = objectMapper.readValue(bodyResponse, OpenWeatherForecastDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }
        return openWeatherForecastDTO;
    }

}
