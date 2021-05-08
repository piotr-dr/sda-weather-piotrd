package com.sda.weather.service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="location", uniqueConstraints = {@UniqueConstraint(columnNames = {"countryName", "cityName"})})
@NoArgsConstructor
@Getter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String countryName;
    String regionName;
    String cityName;
    @Column(precision = 2, scale = 6)
    @Type(type = "double")
    double latitude;
    @Column(precision = 3, scale = 6)
    @Type(type = "double")
    double longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    List<WeatherInfo> weatherInfoList = new ArrayList<>();

    public Location(String countryName, String regionName, String cityName, double latitude, double longitude) {
        this.countryName = countryName;
        this.regionName = regionName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addWeatherInfo(WeatherInfo weatherInfo) {
        weatherInfoList.add(weatherInfo);
        weatherInfo.setLocation(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
