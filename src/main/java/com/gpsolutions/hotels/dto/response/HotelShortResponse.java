package com.gpsolutions.hotels.dto.response;

import lombok.Data;

@Data
public class HotelShortResponse {
    private long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
