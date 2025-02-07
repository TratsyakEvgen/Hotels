package com.gpsolutions.hotels.service.impl;

import com.gpsolutions.hotels.dto.request.CreateHotel;
import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.entity.Hotel;
import com.gpsolutions.hotels.exception.EntityNotFoundException;
import com.gpsolutions.hotels.mapper.HotelMapper;
import com.gpsolutions.hotels.repository.HotelRepository;
import com.gpsolutions.hotels.service.HotelService;
import com.gpsolutions.hotels.service.HotelSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultHotelService implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final HotelSpecificationBuilder hotelSpecificationBuilder;

    @Override
    public List<HotelShortResponse> getAll() {
        return hotelRepository.findAll()
                .stream()
                .map(hotelMapper::toHotelShortResponse)
                .toList();
    }

    @Override
    public HotelResponse find(long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toHotelResponse)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with id " + id + " not found"));
    }

    @Override
    public List<HotelShortResponse> search(SearchFilter searchFilter) {
        Specification<Hotel> specification = hotelSpecificationBuilder.build(searchFilter);
        return hotelRepository.findAll(specification)
                .stream()
                .map(hotelMapper::toHotelShortResponse)
                .toList();
    }

    @Override
    public HotelShortResponse create(CreateHotel createHotel) {
        Hotel hotel = hotelMapper.toHotel(createHotel);
        hotelRepository.save(hotel);
        return hotelMapper.toHotelShortResponse(hotel);
    }

}
