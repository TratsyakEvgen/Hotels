package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.enums.GroupingFieldHotel;
import com.gpsolutions.hotels.service.HistogramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/histogram")
@ApiResponse(responseCode = "200", useReturnTypeSchema = true)
public class HistogramController {
    private final HistogramService histogramService;

    @Operation(summary = "Get the number of hotels grouped by parameter", tags = "histograms")
    @GetMapping("/{parameter}")
    private Map<String, Long> groupBy(
            @Parameter(required = true, schema = @Schema(allowableValues = {"brand", "city", "country", "amenities"}))
            @PathVariable("parameter")
            GroupingFieldHotel groupingFieldHotel) {
        return histogramService.groupBy(groupingFieldHotel);
    }
}
