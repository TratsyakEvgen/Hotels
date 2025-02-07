package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.dto.response.ErrorResponse;
import com.gpsolutions.hotels.service.AmenityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ApiResponse(responseCode = "200", useReturnTypeSchema = true)
public class AmenityController {
    private final AmenityService amenityService;
    @Operation(summary = "Add amenities for hotel", tags = "amenities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/hotels/{hotelId}/amenities")
    public void update(@PathVariable long hotelId, @RequestBody List<String> amenitiesName) {
        amenityService.addAmenities(hotelId, amenitiesName);
    }
}
