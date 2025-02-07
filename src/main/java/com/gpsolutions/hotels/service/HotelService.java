package com.gpsolutions.hotels.service;

import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.enums.GroupingField;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<HotelShortResponse> getAll();

    HotelResponse find(long id);

    List<HotelShortResponse> search(SearchFilter searchFilter);

    Map<String, Long> groupBy(GroupingField groupingField);
}
