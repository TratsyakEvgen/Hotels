package com.gpsolutions.hotels.service;

import com.gpsolutions.hotels.dto.request.SearchFilter;
import com.gpsolutions.hotels.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecificationBuilder {
    Specification<Hotel> build(SearchFilter searchFilter);
}
