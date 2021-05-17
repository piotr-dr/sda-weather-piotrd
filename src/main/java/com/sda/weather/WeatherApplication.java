package com.sda.weather;

import com.sda.weather.controller.LocationController;
import com.sda.weather.controller.LocationMapper;
import com.sda.weather.controller.WeatherInfoController;
import com.sda.weather.controller.WeatherInfoMapper;
import com.sda.weather.service.currentweatherfactory.WeatherDTOFactory;
import com.sda.weather.service.currentweatherfactory.openweathermap.OpenWeatherDTOFactory;
import com.sda.weather.service.currentweatherfactory.weatherbit.WeatherBitDTOFactory;
import com.sda.weather.service.currentweatherfactory.weatherstack.WeatherStackDTOFactory;
import com.sda.weather.repository.LocationDAO;
import com.sda.weather.repository.LocationDAOImpl;
import com.sda.weather.repository.WeatherInfoDAO;
import com.sda.weather.repository.WeatherInfoDAOImpl;
import com.sda.weather.service.LocationCreatorService;
import com.sda.weather.service.LocationProviderService;
import com.sda.weather.service.WeatherInfoService;
import com.sda.weather.service.weatherforecastfactory.WeatherForecastDTOFactory;
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
        LocationCreatorService locationCreatorService = new LocationCreatorService(locationDAO);
        LocationMapper locationMapper = new LocationMapper();
        LocationProviderService locationProviderService = new LocationProviderService(locationDAO);
        LocationController locationController = new LocationController(locationCreatorService, locationProviderService,
                locationMapper);
        WeatherDTOFactory[] weatherDTOFactories = {new OpenWeatherDTOFactory(), new WeatherBitDTOFactory(),
                new WeatherStackDTOFactory()};
        WeatherForecastDTOFactory weatherForecastDTOFactory = new WeatherForecastDTOFactory();
        WeatherInfoService weatherInfoService = new WeatherInfoService(weatherDTOFactories, weatherForecastDTOFactory,
                weatherInfoDAO, locationProviderService);
        WeatherInfoMapper weatherInfoMapper = new WeatherInfoMapper();
        WeatherInfoController weatherInfoController = new WeatherInfoController(weatherInfoService, weatherInfoMapper);


        UserInterface userInterface = new UserInterface(locationController, weatherInfoController);

        userInterface.runApplication();
    }
}
