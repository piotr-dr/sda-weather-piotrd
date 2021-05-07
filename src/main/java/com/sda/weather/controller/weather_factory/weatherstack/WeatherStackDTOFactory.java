package com.sda.weather.controller.weather_factory.weatherstack;

import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.controller.weather_factory.WeatherDTO;
import com.sda.weather.controller.weather_factory.WeatherDTOFactory;

import java.io.DataInput;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class WeatherStackDTOFactory implements WeatherDTOFactory {

    private String ak = "c1462298d58e03161410776ad52a3abc";

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {
        WeatherStackDTO weatherStackDTO;
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://api.weatherstack.com/current" +
                            "?access_key=" + ak +
                            "&query=" + cityName + "," + countryName))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String response = httpResponse.body();

            weatherStackDTO = WeatherObjectMapper.getObjectMapper().readValue(response, WeatherStackDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }
        return weatherStackDTO;
    }
}
