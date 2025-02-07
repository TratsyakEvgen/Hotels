package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AmenityController {
    private final AmenityService amenityService;

    @PostMapping("/hotels/{hotelId}/amenities")
    public void update(@PathVariable long hotelId, @RequestBody List<String> amenitiesName) {
        amenityService.addAmenities(hotelId, amenitiesName);
    }
}
