package com.sda.weather.repository;

import com.sda.weather.service.entities.WeatherInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WeatherInfoDAOImpl implements WeatherInfoDAO{

    private SessionFactory sessionFactory;

    public WeatherInfoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public WeatherInfo saveWeatherInfo(WeatherInfo weatherInfo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        WeatherInfo takenInfo = null;
        if(session.get(WeatherInfo.class, 1L) != null) {
            takenInfo = session.get(WeatherInfo.class, 1L);
            session.update(takenInfo);
            takenInfo.setWindSpeed(weatherInfo.getWindSpeed());
            takenInfo.setWindDirection(weatherInfo.getWindDirection());
            takenInfo.setHumidity(weatherInfo.getHumidity());
            takenInfo.setPressure(weatherInfo.getPressure());
            takenInfo.setTemperature(weatherInfo.getTemperature());
        } else {
            session.persist(weatherInfo);
        }

        transaction.commit();
        session.close();

        return takenInfo;
    }
}
