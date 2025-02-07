package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.enums.GroupingFieldHotel;
import com.gpsolutions.hotels.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/histogram")
public class HistogramController {
    private final HistogramService histogramService;

    @GetMapping("/{parameter}")
    private Map<String, Long> groupBy(@PathVariable("parameter") GroupingFieldHotel groupingFieldHotel) {
        return histogramService.groupBy(groupingFieldHotel);
    }
}
