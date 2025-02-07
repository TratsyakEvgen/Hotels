package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.dto.request.CreateHotel;
import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/hotels")
    public HotelShortResponse create(@Valid @RequestBody CreateHotel createHotel) {
        return hotelService.create(createHotel);
    }


}
