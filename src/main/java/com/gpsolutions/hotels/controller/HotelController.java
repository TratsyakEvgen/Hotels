package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.dto.request.CreateHotel;
import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.ErrorResponse;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ApiResponse(responseCode = "200", useReturnTypeSchema = true)
public class HotelController {
    private final HotelService hotelService;

    @Operation(summary = "Get all hotels", tags = "hotels")
    @GetMapping("/hotels")
    public List<HotelShortResponse> getAll() {
        return hotelService.getAll();
    }

    @Operation(summary = "Get hotel by id", tags = "hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/hotels/{id}")
    public HotelResponse find(@PathVariable long id) {
        return hotelService.find(id);
    }

    @Operation(summary = "Search hotels", tags = "hotels")
    @GetMapping("/search")
    private List<HotelShortResponse> search(@ParameterObject @ModelAttribute SearchFilter searchFilter) {
        return hotelService.search(searchFilter);
    }

    @Operation(summary = "Create new hotel", tags = "hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Data is not valid",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/hotels")
    public HotelShortResponse create(@Valid @RequestBody CreateHotel createHotel) {
        return hotelService.create(createHotel);
    }


}
