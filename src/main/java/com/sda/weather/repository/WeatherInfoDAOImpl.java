package com.sda.weather.repository;

import com.sda.weather.service.entities.WeatherInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class WeatherInfoDAOImpl implements WeatherInfoDAO{

    private SessionFactory sessionFactory;

    public WeatherInfoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public WeatherInfo saveWeather(WeatherInfo weatherInfo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        WeatherInfo savedWeatherInfo = null;
        Optional<WeatherInfo> takenWI = session.createQuery("SELECT wi FROM WeatherInfo AS wi WHERE wi.location = :location AND wi.date = :date", WeatherInfo.class)
                .setParameter("location", weatherInfo.getLocation())
                .setParameter("date", weatherInfo.getDate())
                .uniqueResultOptional();
        if(takenWI.isPresent()) {
            session.update(takenWI.get());
            takenWI.get().setTemperature(weatherInfo.getTemperature());
            takenWI.get().setPressure(weatherInfo.getPressure());
            takenWI.get().setHumidity(weatherInfo.getHumidity());
            takenWI.get().setWindDirection(weatherInfo.getWindDirection());
            takenWI.get().setWindSpeed(weatherInfo.getWindSpeed());
            savedWeatherInfo = takenWI.get();
        } else {
            session.persist(weatherInfo);
            savedWeatherInfo = weatherInfo;
        }

        transaction.commit();
        session.close();

        return savedWeatherInfo;
    }

}
