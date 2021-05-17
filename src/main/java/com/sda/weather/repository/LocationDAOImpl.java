package com.sda.weather.repository;

import com.sda.weather.service.entities.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

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
    public Optional<Location> getLocation(Long locationId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<Location> location = session.byId(Location.class).loadOptional(locationId);

        transaction.commit();
        session.close();
        return location;
    }


}
