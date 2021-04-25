package com.sda.weather;

import java.util.Scanner;

public class UserInterface {

    AddingLocationController addingLocationController;

    public UserInterface(AddingLocationController addingLocationController) {
        this.addingLocationController = addingLocationController;
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
                case 1:
                    addNewLocation();
            }

        }
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
        System.out.println("Type longitude:");
        double longitude = scanner.nextDouble();
        String response = addingLocationController.addNewLocation(country, region, city, latitude, longitude);
        System.out.println("New location has been added: " + response);
    }

}
