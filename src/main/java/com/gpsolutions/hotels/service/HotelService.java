package com.gpsolutions.hotels.service;

import com.gpsolutions.hotels.dto.request.CreateHotel;
import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;

import java.util.List;

public interface HotelService {
    List<HotelShortResponse> getAll();

    HotelResponse find(long id);

    List<HotelShortResponse> search(SearchFilter searchFilter);

    HotelShortResponse create(CreateHotel createHotel);

}
