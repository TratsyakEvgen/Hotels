package com.gpsolutions.hotels.repository;

import com.gpsolutions.hotels.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    List<Amenity> findAmenitiesByNameIgnoreCaseIn(List<String> names);
}