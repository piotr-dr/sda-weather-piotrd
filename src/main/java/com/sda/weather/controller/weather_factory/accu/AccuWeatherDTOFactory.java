package com.sda.weather.controller.weather_factory.accu;

import com.sda.weather.controller.WeatherObjectMapper;
import com.sda.weather.controller.weather_factory.WeatherDTO;
import com.sda.weather.controller.weather_factory.WeatherDTOFactory;
import com.sda.weather.controller.weather_factory.weatherstack.WeatherStackDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class AccuWeatherDTOFactory implements WeatherDTOFactory {

    @Override
    public WeatherDTO downloadWeather(String cityName, String countryName, LocalDate date) {
        AccuWeatherDTO accuWeatherDTO;
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://dataservice.accuweather.com/forecasts/v1/daily/10day/" + getLocationKey(cityName, countryName)))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String response = httpResponse.body();
            System.out.println(response);

            //accuWeatherDTO = WeatherObjectMapper.getObjectMapper().readValue(response, AccuWeatherDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }
        return null;
    }

    public String getLocationKey(String cityName, String countryName) {
        String locationKey = "";
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=nA2k61MDFT7AnK4aqq7U0GTAA3CAJCVE&q="
                            + cityName + "%20" + countryName))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String response = httpResponse.body();

            LocationKeyDTO[] locationKeyDTO = WeatherObjectMapper.getObjectMapper().readValue(response, LocationKeyDTO[].class);
            locationKey = locationKeyDTO[0].getKey();

        } catch (Exception e) {
            throw new RuntimeException("Internal server error");
        }

        return locationKey;
    }
}
