package com.sda.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.weather.controller.AddingLocationController;
import com.sda.weather.controller.GettingLocationController;

import java.util.Scanner;

public class UserInterface {

    AddingLocationController addingLocationController;
    GettingLocationController gettingLocationController;

    public UserInterface(AddingLocationController addingLocationController, GettingLocationController gettingLocationController) {
        this.addingLocationController = addingLocationController;
        this.gettingLocationController = gettingLocationController;
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
            }

        }
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
