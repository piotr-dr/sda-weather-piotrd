package com.sda.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.AddingLocationController;
import com.sda.weather.controller.GettingLocationController;
import com.sda.weather.controller.WeatherInfoController;
import com.sda.weather.service.DateValidatorService;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {

    AddingLocationController addingLocationController;
    GettingLocationController gettingLocationController;
    WeatherInfoController weatherInfoController;

    public UserInterface(AddingLocationController addingLocationController,
                         GettingLocationController gettingLocationController,
                         WeatherInfoController weatherInfoController) {
        this.addingLocationController = addingLocationController;
        this.gettingLocationController = gettingLocationController;
        this.weatherInfoController = weatherInfoController;
    }

    void runApplication() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome in Weather Application.");
            System.out.println("@2021 Drozd Ltd.\n");
            System.out.println("MENU:");
            System.out.println("-------------------------------");
            System.out.println("1. Add location.");
            System.out.println("2. Show added locations.");
            System.out.println("3. Get weather information.");
            System.out.println("0. Exit.");

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
        System.out.println("INFO:\n" + "You can get weather data up to 14 days in advance.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the city name:");
        String cityName = scanner.nextLine();
        if (cityName.isEmpty()) {
            throw new RuntimeException("City's name can't be empty.");
        }
        System.out.println("Enter the country name:");
        String countryName = scanner.nextLine();
        if (countryName.isEmpty()) {
            throw new RuntimeException("Country's name can't be empty.");
        }
        System.out.println("Enter the date: [yyyy-mm-dd]");
        String date = scanner.nextLine();
        LocalDate validatedDate = DateValidatorService.validate(date);
        String weatherInfo = weatherInfoController.getWeatherInfo(cityName, countryName, validatedDate);
        System.out.printf("Weather's info for %s, %s for %s:\n%s\n", cityName, countryName, date, weatherInfo);
    }

    private void showLocations() {
        String locations = null;
        try {
            locations = gettingLocationController.getLocations();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.printf("Added locations:\n%s\n\n", locations);
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
        String response = addingLocationController.addNewLocation(country, region, city, latitude, longitude);
        System.out.printf("New location has been added:\n%s\n\n", response);
    }

}
