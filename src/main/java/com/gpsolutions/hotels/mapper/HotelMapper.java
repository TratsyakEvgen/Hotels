package com.gpsolutions.hotels.mapper;

import com.gpsolutions.hotels.dto.request.CreateHotel;
import com.gpsolutions.hotels.dto.response.HotelResponse;
import com.gpsolutions.hotels.dto.response.HotelShortResponse;
import com.gpsolutions.hotels.entity.Hotel;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AmenityMapper.class})
public interface HotelMapper {
    @Mapping(source = ".", target = "address", qualifiedByName = "getAddress")
    HotelShortResponse toHotelShortResponse(Hotel hotel);

    @Mapping(target = "address", source = ".")
    @Mapping(target = "contacts", source = ".")
    @Mapping(target = "arrivalTime", source = ".")
    HotelResponse toHotelResponse(Hotel hotel);

    @Mapping(target = ".", source = "address")
    @Mapping(target = ".", source = "contacts")
    @Mapping(target = ".", source = "arrivalTime")
    Hotel toHotel(CreateHotel createHotel);

    @Named("getAddress")
    default String getAddress(Hotel hotel) {
        return String.format("%d %s, %s, %s, %s",
                hotel.getHouseNumber(),
                hotel.getStreet(),
                hotel.getCity(),
                hotel.getPostCode(),
                hotel.getCounty());

    }
}