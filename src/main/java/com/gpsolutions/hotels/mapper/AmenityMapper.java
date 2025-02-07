package com.gpsolutions.hotels.mapper;

import com.gpsolutions.hotels.entity.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AmenityMapper {
    default List<String> toListNames(Set<Amenity> amenities) {
        return amenities.stream()
                .map(Amenity::getName)
                .toList();
    }

    ;
}
