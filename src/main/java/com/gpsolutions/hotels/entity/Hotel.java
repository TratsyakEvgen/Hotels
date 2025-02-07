package com.gpsolutions.hotels.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String brand;
    private int houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
    private String phone;
    private String email;
    private LocalTime checkIn;
    private LocalTime checkOut;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities;
}