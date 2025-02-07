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

//    @Query("select new com.gpsolutions.hotels.dto.GroupHotels(h.brand, count(h)) from Hotel h group by h.brand")
//    List<GroupHotels> groupByBrand();
//
//    @Query("select new com.gpsolutions.hotels.dto.GroupHotels(h.city, count(h)) from Hotel h group by h.city")
//    List<GroupHotels> groupByCity();
//
//    @Query("select new com.gpsolutions.hotels.dto.GroupHotels(h.country, count(h)) from Hotel h group by h.country")
//    List<GroupHotels> groupByCountry();
//
//    @Query("select new com.gpsolutions.hotels.dto.GroupHotels(a.name, count(h)) from Hotel h join h.amenities a group by a.name")
//    List<GroupHotels> groupByAmenities();
}