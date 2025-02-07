package com.gpsolutions.hotels.configuration;

import com.gpsolutions.hotels.enums.GroupingField;
import org.springframework.core.convert.converter.Converter;

public class GroupingFieldConverter implements Converter<String, GroupingField> {
    @Override
    public GroupingField convert(String source) {
        return GroupingField.valueOf(source.toUpperCase());
    }
}
