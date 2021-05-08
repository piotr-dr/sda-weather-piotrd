package com.sda.weather.repository;

import com.sda.weather.service.entities.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class LocationDAOImpl implements LocationDAO {

    private SessionFactory sessionFactory;

    public LocationDAOImpl(SessionFactory sessionFactory) {
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

    @Override
    public List<Location> getLocations() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Location> locations = session.createQuery("SELECT l FROM Location AS l", Location.class)
                .getResultList();

        transaction.commit();
        session.close();
        return locations;
    }

    @Override
    public Location getLocation(String cityName, String countryName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Location addedLocation;

        try {
            addedLocation = session.createQuery("SELECT l FROM Location AS l WHERE l.cityName = :cityName " +
                    "AND l.countryName = :countryName", Location.class)
                    .setParameter("cityName", cityName)
                    .setParameter("countryName", countryName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException("This location doesn't exist in database.");
        }

        transaction.commit();
        session.close();
        return addedLocation;
    }


}
