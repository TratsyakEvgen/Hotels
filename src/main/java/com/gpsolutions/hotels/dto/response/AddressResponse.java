package com.gpsolutions.hotels.dto.response;

import lombok.Data;

@Data
public class AddressResponse {
    private int houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;
}
