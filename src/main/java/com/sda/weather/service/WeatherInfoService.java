package com.sda.weather.service;

import com.sda.weather.service.currentweatherfactory.WeatherDTO;
import com.sda.weather.service.currentweatherfactory.WeatherDTOFactory;
import com.sda.weather.repository.WeatherInfoDAO;
import com.sda.weather.service.entities.Location;
import com.sda.weather.service.entities.WeatherInfo;
import com.sda.weather.service.weatherforecastfactory.WeatherForecastDTO;
import com.sda.weather.service.weatherforecastfactory.WeatherForecastDTOFactory;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherInfoService {

    private WeatherDTOFactory[] weatherDTOFactories;
    private WeatherForecastDTOFactory weatherForecastDTOFactory;
    private LocationProviderService locationProviderService;
    private WeatherInfoDAO weatherInfoDAO;
    private List<Double> temperatureList;
    private List<Double> humidityList;
    private List<Double> pressureList;
    private List<Double> windDirectionList;
    private List<Double> windSpeedList;

    public WeatherInfoService(WeatherDTOFactory[] weatherDTOFactories, WeatherForecastDTOFactory weatherForecastDTOFactory,
                              WeatherInfoDAO weatherInfoDAO,
                              LocationProviderService locationProviderService) {
        this.weatherDTOFactories = weatherDTOFactories;
        this.weatherForecastDTOFactory = weatherForecastDTOFactory;
        this.locationProviderService = locationProviderService;
        this.weatherInfoDAO = weatherInfoDAO;
        this.temperatureList = new ArrayList<>();
        this.humidityList = new ArrayList<>();
        this.pressureList = new ArrayList<>();
        this.windDirectionList = new ArrayList<>();
        this.windSpeedList = new ArrayList<>();
    }

    public WeatherInfo getWeatherInfo(Long locationId, LocalDate date) {
        Location addedLocation = locationProviderService.getLocation(locationId)
                .orElseThrow(() -> new RuntimeException("Unknown id number"));
        WeatherInfo savedWeatherInfo = null;
        if(isCurrentDate(date)) {
            for (WeatherDTOFactory dtoFactory : weatherDTOFactories) {
                WeatherDTO weatherDTO = dtoFactory.downloadWeather(addedLocation.getCityName(), addedLocation.getCountryName(), date);
                temperatureList.add(Double.parseDouble(weatherDTO.getTemperature()));
                humidityList.add(Double.parseDouble(weatherDTO.getHumidity()));
                pressureList.add(Double.parseDouble(weatherDTO.getPressure()));
                windDirectionList.add(Double.parseDouble(weatherDTO.getWindDirection()));
                windSpeedList.add(Double.parseDouble(weatherDTO.getWindSpeed()));
            }
            WeatherInfo averagedWeatherInfo = avarageResult();
            averagedWeatherInfo.setLocation(addedLocation);
            savedWeatherInfo = weatherInfoDAO.saveWeather(averagedWeatherInfo);
        } else {
            WeatherForecastDTO weatherForecastDTO = weatherForecastDTOFactory.getFromOpenWeatherMap
                    (addedLocation.getLatitude(), addedLocation.getLongitude(), date);
            int dayNumber = date.compareTo(LocalDate.now());
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setDate(DateValidationService.convertToDatabase(weatherForecastDTO.getDate(dayNumber)));
            weatherInfo.setTemperature(Double.parseDouble(weatherForecastDTO.getTemperature(dayNumber)));
            weatherInfo.setHumidity(Double.parseDouble(weatherForecastDTO.getHumidity(dayNumber)));
            weatherInfo.setPressure(Double.parseDouble(weatherForecastDTO.getPressure(dayNumber)));
            weatherInfo.setWindDirection(Double.parseDouble(weatherForecastDTO.getWindDirection(dayNumber)));
            weatherInfo.setWindSpeed(Double.parseDouble(weatherForecastDTO.getWindSpeed(dayNumber)));
            weatherInfo.setLocation(addedLocation);
            savedWeatherInfo = weatherInfoDAO.saveWeather(weatherInfo);
        }
        return savedWeatherInfo;
    }

    public void clearWeatherLists() {
        temperatureList.clear();
        humidityList.clear();
        pressureList.clear();
        windDirectionList.clear();
        windSpeedList.clear();
    }

    private WeatherInfo avarageResult() {
        WeatherInfo weatherInfo = new WeatherInfo();

        Double averageTemperature = Double.valueOf(temperatureList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));
        weatherInfo.setTemperature(averageTemperature);

        Double averageHumidity = Double.valueOf(humidityList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));
        weatherInfo.setHumidity(averageHumidity);

        Double averagePressure = Double.valueOf(pressureList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));
        weatherInfo.setPressure(averagePressure);

        Double averageWindDirection = Double.valueOf(windDirectionList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));
        weatherInfo.setWindDirection(averageWindDirection);

        Double averageWindSpeed = Double.valueOf(windSpeedList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));
        weatherInfo.setWindSpeed(averageWindSpeed);

        weatherInfo.setDate(Date.valueOf(LocalDate.now()));

        return weatherInfo;
    }

    private boolean isCurrentDate (LocalDate date) {
        return date == LocalDate.now();
    }
}
