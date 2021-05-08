package com.sda.weather.service;

import com.sda.weather.controller.weather_factory.WeatherDTO;
import com.sda.weather.controller.weather_factory.WeatherDTOFactory;
import com.sda.weather.repository.WeatherInfoDAO;
import com.sda.weather.service.entities.Location;
import com.sda.weather.service.entities.WeatherInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Getter
@Setter
public class WeatherInfoService {

    private final WeatherDTOFactory[] WEATHER_DTO_FACTORIES; // todo use camelCase
    private LocationValidationService locationValidationService;
    private WeatherInfoDAO weatherInfoDAO;

    // todo move it to a method
    private List<Double> temperatureList;
    private List<Double> humidityList;
    private List<Double> pressureList;
    private List<Double> windDirectionList;
    private List<Double> windSpeedList;

    public WeatherInfoService(WeatherDTOFactory[] weatherDTOFactory, WeatherInfoDAO weatherInfoDAO,
                              LocationValidationService locationValidationService) {
        this.WEATHER_DTO_FACTORIES = weatherDTOFactory;
        this.locationValidationService = locationValidationService;
        this.weatherInfoDAO = weatherInfoDAO;
    }

    public WeatherInfo getWeatherInfo(String cityName, String countryName, LocalDate date) {
        Location addedLocation = locationValidationService.isAdded(cityName, countryName); // todo you can use Optional<Location> location = locationService.getLocation(id)
        this.temperatureList = new ArrayList<>();
        this.humidityList = new ArrayList<>();
        this.pressureList = new ArrayList<>();
        this.windDirectionList = new ArrayList<>();
        this.windSpeedList = new ArrayList<>();
        for (WeatherDTOFactory dtoFactory : WEATHER_DTO_FACTORIES) {
            WeatherDTO weatherDTO = dtoFactory.downloadWeather(cityName, countryName, date);
            temperatureList.add(Double.parseDouble(weatherDTO.getTemperature()));
            humidityList.add(Double.parseDouble(weatherDTO.getHumidity()));
            pressureList.add(Double.parseDouble(weatherDTO.getPressure()));
            windDirectionList.add(Double.parseDouble(weatherDTO.getWindDirection()));
            windSpeedList.add(Double.parseDouble(weatherDTO.getWindSpeed()));
        }
        WeatherInfo averagedWeatherInfo = avarageResult();
        averagedWeatherInfo.setLocation(addedLocation);
        WeatherInfo savedWeatherInfo = weatherInfoDAO.saveWeatherInfo(averagedWeatherInfo);
        return savedWeatherInfo;
    }

    private WeatherInfo avarageResult() {
        WeatherInfo weatherInfo = new WeatherInfo();
        Double averageTemperature = temperatureList.stream()
                .collect(Collectors.summingDouble(Double::doubleValue)) / 3;
        weatherInfo.setTemperature(averageTemperature);

        temperatureList.stream();

        // double value = temperatureList.stream()
        //        .mapToDouble(d -> d)
        //        .average()
        //        .orElse(0);

        Double averageHumidity = humidityList.stream()
                .collect(Collectors.summingDouble(Double::doubleValue)) / 3;
        weatherInfo.setHumidity(averageHumidity);

        Double averagePressure = pressureList.stream()
                .collect(Collectors.summingDouble(Double::doubleValue)) / 3;
        weatherInfo.setPressure(averagePressure);

        Double averageWindDirection = windDirectionList.stream()
                .collect(Collectors.summingDouble(Double::doubleValue)) / 3;
        weatherInfo.setWindDirection(averageWindDirection);

        Double averageWindSpeed = windSpeedList.stream()
                .collect(Collectors.summingDouble(Double::doubleValue)) / 3;
        weatherInfo.setWindSpeed(averageWindSpeed);

        return weatherInfo;
    }
}
