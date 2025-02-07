package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.enums.GroupingField;
import com.gpsolutions.hotels.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortResponse> getAll() {
        return hotelService.getAll();
    }

    @GetMapping("/hotels/{id}")
    public HotelResponse find(@PathVariable long id) {
        return hotelService.find(id);
    }

    @GetMapping("/search")
    private List<HotelShortResponse> search(@ModelAttribute SearchFilter searchFilter) {
        return hotelService.search(searchFilter);
    }

    @GetMapping("/histogram/{parameter}")
    private Map<String, Long> groupBy(@PathVariable("parameter") GroupingField groupingField) {
        return hotelService.groupBy(groupingField);
    }
}
