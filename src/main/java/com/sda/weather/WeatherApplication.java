package com.sda.weather;

import com.sda.weather.controller.AddingLocationController;
import com.sda.weather.controller.GettingLocationController;
import com.sda.weather.controller.WeatherInfoController;
import com.sda.weather.controller.weather_factory.WeatherDTOFactory;
import com.sda.weather.controller.weather_factory.openweathermap.OpenWeatherDTOFactory;
import com.sda.weather.controller.weather_factory.weatherbit.WeatherBitDTOFactory;
import com.sda.weather.controller.weather_factory.weatherstack.WeatherStackDTOFactory;
import com.sda.weather.repository.LocationDAO;
import com.sda.weather.repository.LocationDAOImpl;
import com.sda.weather.repository.WeatherInfoDAO;
import com.sda.weather.repository.WeatherInfoDAOImpl;
import com.sda.weather.service.AddingLocationService;
import com.sda.weather.service.GettingLocationService;
import com.sda.weather.service.LocationValidationService;
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

        LocationDAO locationDAO = new LocationDAOImpl(sessionFactory);
        WeatherInfoDAO weatherInfoDAO = new WeatherInfoDAOImpl(sessionFactory);
        AddingLocationService addingLocationService = new AddingLocationService(locationDAO);
        AddingLocationController addingLocationController = new AddingLocationController(addingLocationService);
        GettingLocationService gettingLocationService = new GettingLocationService(locationDAO);
        GettingLocationController gettingLocationController = new GettingLocationController(gettingLocationService);
        WeatherDTOFactory[] weatherDTOFactories = {new OpenWeatherDTOFactory(), new WeatherBitDTOFactory(), new WeatherStackDTOFactory()};
        LocationValidationService locationValidationService = new LocationValidationService(locationDAO);
        WeatherInfoService weatherInfoService = new WeatherInfoService(weatherDTOFactories, weatherInfoDAO, locationValidationService);
        WeatherInfoController weatherInfoController = new WeatherInfoController(weatherInfoService);


        UserInterface userInterface = new UserInterface(addingLocationController, gettingLocationController, weatherInfoController);

        userInterface.runApplication();
    }
}
