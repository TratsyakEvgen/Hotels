package com.gpsolutions.hotels.repository;

import com.gpsolutions.hotels.entity.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HotelRepository extends
        JpaRepository<Hotel, Long>,
        JpaSpecificationExecutor<Hotel>,
        GroupByFieldRepository {
    @EntityGraph(attributePaths = "amenities")
    Optional<Hotel> findById(long id);
}