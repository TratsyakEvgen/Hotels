package com.gpsolutions.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull(message = "Contacts time must not be null")
public class Contacts {
    @NotBlank(message = "Phone number must not be blank")
    private String phone;
    @NotBlank(message = "Email must not be blank")
    private String email;
}
