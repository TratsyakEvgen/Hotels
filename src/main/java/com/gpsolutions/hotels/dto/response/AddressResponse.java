package com.gpsolutions.hotels.dto;

import lombok.Data;

@Data
public class AddressResponse {
    private int houseNumber;
    private String street;
    private String city;
    private String country;
    private String postCode;
}
