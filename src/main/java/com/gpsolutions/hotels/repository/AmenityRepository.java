package com.gpsolutions.hotels.repository;

import com.gpsolutions.hotels.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}