package com.gpsolutions.hotels.dto.response;

import com.gpsolutions.hotels.dto.Address;
import com.gpsolutions.hotels.dto.ArrivalTime;
import com.gpsolutions.hotels.dto.Contacts;
import lombok.Data;

import java.util.List;

@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String brand;
    private Address address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;


}
