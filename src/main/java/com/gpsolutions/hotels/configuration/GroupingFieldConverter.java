package com.gpsolutions.hotels.configuration;

import com.gpsolutions.hotels.enums.GroupingFieldHotel;
import org.springframework.core.convert.converter.Converter;

public class GroupingFieldConverter implements Converter<String, GroupingFieldHotel> {
    @Override
    public GroupingFieldHotel convert(String source) {
        return GroupingFieldHotel.valueOf(source.toUpperCase());
    }
}
