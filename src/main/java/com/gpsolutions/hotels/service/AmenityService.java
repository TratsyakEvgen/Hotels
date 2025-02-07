package com.gpsolutions.hotels.service;

import java.util.List;

public interface AmenityService {
    void addAmenities(long hotelId, List<String> amenitiesName);
}
