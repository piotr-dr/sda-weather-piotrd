package com.sda.weather;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class WeatherApplication {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        WeatherRepositoryImpl weatherRepository = new WeatherRepositoryImpl(sessionFactory);
        AddingLocationService addingLocationService = new AddingLocationService(weatherRepository);
        AddingLocationController addingLocationController = new AddingLocationController(addingLocationService);
        UserInterface userInterface = new UserInterface(addingLocationController);

        userInterface.runApplication();
    }

}
