package com.gpsolutions.hotels.service.impl;

import com.gpsolutions.hotels.dto.GroupedHotels;
import com.gpsolutions.hotels.enums.GroupingFieldHotel;
import com.gpsolutions.hotels.repository.HotelRepository;
import com.gpsolutions.hotels.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultHistogramService implements HistogramService {
    private final HotelRepository hotelRepository;

    @Override
    public Map<String, Long> groupBy(GroupingFieldHotel groupingFieldHotel) {
        return hotelRepository.groupByField(groupingFieldHotel)
                .stream()
                .collect(Collectors.toMap(GroupedHotels::getName, GroupedHotels::getCount));
    }
}
