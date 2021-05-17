package com.sda.weather.service.currentweatherfactory.weatherstack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.service.currentweatherfactory.WeatherDTO;
import com.sda.weather.service.currentweatherfactory.WeatherDTOFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class WeatherStackDTOFactory implements WeatherDTOFactory {

    private String AK;
    private ObjectMapper objectMapper;

    public WeatherStackDTOFactory() {
        this.AK = "c1462298d58e03161410776ad52a3abc";
        this.objectMapper = WeatherObjectMapper.getObjectMapper();
    }

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {
        WeatherStackDTO weatherStackDTO;
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://api.weatherstack.com/current" +
                            "?access_key=" + AK +
                            "&query=" + cityName + "," + countryName))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String response = httpResponse.body();

            weatherStackDTO = objectMapper.readValue(response, WeatherStackDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }
        return weatherStackDTO;
    }
}
