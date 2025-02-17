package com.gpsolutions.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Contacts {
    @NotBlank(message = "Phone number must not be blank")
    private String phone;
    @NotBlank(message = "Email must not be blank")
    private String email;
}
