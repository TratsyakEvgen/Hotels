package com.gpsolutions.hotels.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressResponse address;
    private ContactResponse contacts;
    private ArrivalTimeResponse arrivalTime;
    private List<String> amenities;


}
