package com.gpsolutions.hotels.service;

import com.gpsolutions.hotels.enums.GroupingFieldHotel;

import java.util.Map;

public interface HistogramService {
    Map<String, Long> groupBy(GroupingFieldHotel groupingFieldHotel);
}
