package com.gpsolutions.hotels.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SearchFilter {
    private List<String> name;
    private List<String> brand;
    private List<String> city;
    private List<String> country;
    private List<String> amenities;
}
