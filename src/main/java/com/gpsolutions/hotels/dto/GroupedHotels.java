package com.gpsolutions.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupedHotels {
    private String name;
    private long count;
}
