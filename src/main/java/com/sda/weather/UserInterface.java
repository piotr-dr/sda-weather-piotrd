package com.sda.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.LocationController;
import com.sda.weather.controller.WeatherInfoController;
import com.sda.weather.service.DateValidationService;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {
    LocationController locationController;
    WeatherInfoController weatherInfoController;

    public UserInterface(LocationController locationController,
                         WeatherInfoController weatherInfoController) {
        this.locationController = locationController;
        this.weatherInfoController = weatherInfoController;
    }

    void runApplication() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n@2021 Weather Forecast Drozd Ltd.");
            System.out.println("\nMENU:");
            System.out.println("-------------------------------");
            System.out.println("1. Add location.");
            System.out.println("2. Show added locations.");
            System.out.println("3. Get weather information.");
            System.out.println("0. Exit.\n");

            int clientChoice = scanner.nextInt();

            switch (clientChoice) {
                case 0:
                    return;
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    showLocations();
                    break;
                case 3:
                    getWeatherInfo();
                    break;
            }

        }
    }

    private void getWeatherInfo() {
        System.out.println("\nINFO:\n" + "You can get weather data up to 7 days in advance.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the location's id:");
        long locationId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter the date: [yyyy-mm-dd]");
        String date = scanner.nextLine();
        LocalDate validatedDate = DateValidationService.validate(date);
        String weatherInfo = weatherInfoController.getWeatherInfo(locationId, validatedDate);
        System.out.printf("\nWeather forecast for %s:\n%s\n", date, weatherInfo);
    }

    private void showLocations() {
        String locations = locationController.getLocations();
        System.out.printf("\nAdded locations:\n%s\n", locations);
    }

    private void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type country's name:");
        String country = scanner.nextLine();
        System.out.println("Type region's name:");
        String region = scanner.nextLine();
        System.out.println("Type city's name:");
        String city = scanner.nextLine();
        System.out.println("Type latitude:");
        double latitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Type longitude:");
        double longitude = scanner.nextDouble();
        scanner.nextLine();
        String response = locationController.addNewLocation(country, region, city, latitude, longitude);
        System.out.printf("\nNew location has been added:\n%s\n", response);
    }

}
