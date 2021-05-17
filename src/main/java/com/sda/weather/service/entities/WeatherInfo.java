package com.sda.weather.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date date;
    @Column(precision = 2, scale = 1)
    @Type(type = "double")
    Double temperature;
    @Column(precision = 4, scale = 1)
    @Type(type = "double")
    Double pressure;
    @Column(precision = 3, scale = 1)
    @Type(type = "double")
    Double humidity;
    @Column(precision = 3, scale = 1)
    @Type(type = "double")
    Double windDirection;
    @Column(precision = 2, scale = 1)
    @Type(type = "double")
    Double windSpeed;
    @ManyToOne
    Location location;

    public void setLocation(Location location) {
        this.location = location;
    }
}
