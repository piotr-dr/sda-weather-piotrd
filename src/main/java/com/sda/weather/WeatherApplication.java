package com.sda.weather;

import com.sda.weather.controller.AddingLocationController;
import com.sda.weather.controller.GettingLocationController;
import com.sda.weather.controller.WeatherInfoController;
import com.sda.weather.repository.WeatherRepository;
import com.sda.weather.repository.WeatherRepositoryImpl;
import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.WeatherInfoService;
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

        WeatherRepository weatherRepository = new WeatherRepositoryImpl(sessionFactory);
        AddingLocationService addingLocationService = new AddingLocationService(weatherRepository);
        AddingLocationController addingLocationController = new AddingLocationController(addingLocationService);
        GettingLocationService gettingLocationService = new GettingLocationService(weatherRepository);
        GettingLocationController gettingLocationController = new GettingLocationController(gettingLocationService);
        WeatherInfoService weatherInfoService = new WeatherInfoService();
        WeatherInfoController weatherInfoController = new WeatherInfoController(weatherInfoService);


        UserInterface userInterface = new UserInterface(addingLocationController, gettingLocationController, weatherInfoController);

        userInterface.runApplication();
    }

}
