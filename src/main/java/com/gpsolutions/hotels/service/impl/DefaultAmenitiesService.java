package com.gpsolutions.hotels.service.impl;

import com.gpsolutions.hotels.entity.Amenity;
import com.gpsolutions.hotels.entity.Hotel;
import com.gpsolutions.hotels.exception.EntityNotFoundException;
import com.gpsolutions.hotels.repository.AmenityRepository;
import com.gpsolutions.hotels.repository.HotelRepository;
import com.gpsolutions.hotels.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultAmenitiesService implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final HotelRepository hotelRepository;

    @Override
    public void addAmenities(long hotelId, List<String> amenitiesName) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with id " + hotelId + " not found"));
        List<Amenity> amenities = getOrCreateAmenities(amenitiesName);
        Set<Amenity> hotelAmenities = hotel.getAmenities();
        hotelAmenities.addAll(amenities);
        hotel.setAmenities(hotelAmenities);
    }


    private List<Amenity> getOrCreateAmenities(List<String> amenitiesNames) {
        Map<String, Amenity> amenityMap = amenityRepository.findAmenitiesByNameIgnoreCaseIn(amenitiesNames)
                .stream()
                .collect(Collectors.toMap(amenity -> amenity.getName().toLowerCase(), Function.identity()));

        List<Amenity> amenitiesToSave = amenitiesNames.stream()
                .map(name -> amenityMap.computeIfAbsent(name.toLowerCase(), Amenity::new))
                .collect(Collectors.toList());

        return amenityRepository.saveAll(amenitiesToSave);
    }
}
