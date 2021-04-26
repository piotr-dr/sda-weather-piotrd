package com.sda.weather.repository;

import com.sda.weather.service.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WeatherRepositoryImpl implements WeatherRepository {

    private SessionFactory sessionFactory;

    public WeatherRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Location saveLocation(Location location) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(location);

        transaction.commit();
        session.close();

        return location;
    }
}
