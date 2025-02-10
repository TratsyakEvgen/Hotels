package com.gpsolutions.hotels.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Address {
    @Min(value = 0, message = "House number must be equals or greater than 0")
    private int houseNumber;
    @NotBlank(message = "Street must not be blank")
    private String street;
    @NotBlank(message = "City must not be blank")
    private String city;
    @NotBlank(message = "County must not be blank")
    private String county;
    @NotBlank(message = "Post code must not be blank")
    private String postCode;
}
