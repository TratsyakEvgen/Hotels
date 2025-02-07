package com.gpsolutions.hotels.repository;

import com.gpsolutions.hotels.dto.GroupedHotels;
import com.gpsolutions.hotels.enums.GroupingFieldHotel;

import java.util.List;

public interface GroupByFieldRepository {
    List<GroupedHotels> groupByField(GroupingFieldHotel groupingFieldHotel);
}
