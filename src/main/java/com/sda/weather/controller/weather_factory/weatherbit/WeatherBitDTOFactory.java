package com.sda.weather.controller.weather_factory.weatherbit;

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

public class WeatherBitDTOFactory implements WeatherDTOFactory {

    private String ak = "9ea1728d72c6415398e9b57737d2f458";

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {

        WeatherbitDTO weatherbitDTO;
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.weatherbit.io/v2.0/current?key=" + ak +
                            "&city=" + cityName))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            String bodyResponse = httpResponse.body();

            weatherbitDTO = WeatherObjectMapper.getObjectMapper().readValue(bodyResponse, WeatherbitDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }

        return weatherbitDTO;
    }
}
