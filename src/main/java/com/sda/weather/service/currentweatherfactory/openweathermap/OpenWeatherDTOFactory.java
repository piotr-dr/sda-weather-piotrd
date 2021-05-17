package com.sda.weather.service.currentweatherfactory.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.service.currentweatherfactory.WeatherDTO;
import com.sda.weather.service.currentweatherfactory.WeatherDTOFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class OpenWeatherDTOFactory implements WeatherDTOFactory {

    private String AK;
    private ObjectMapper objectMapper;

    public OpenWeatherDTOFactory() {
        this.AK = "abc8c6e2e5d31d8874b98f270036015a";
        this.objectMapper = WeatherObjectMapper.getObjectMapper();
    }

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {

        OpenWeatherDTO openWeatherDTO;
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                            "&appid=" + AK + "&units=metric"))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = httpResponse.body();

            openWeatherDTO = objectMapper.readValue(bodyResponse, OpenWeatherDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }

        return openWeatherDTO;
    }
}
