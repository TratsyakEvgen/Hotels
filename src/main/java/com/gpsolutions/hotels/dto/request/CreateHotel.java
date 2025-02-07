package com.gpsolutions.hotels.dto.request;

import com.gpsolutions.hotels.dto.Address;
import com.gpsolutions.hotels.dto.ArrivalTime;
import com.gpsolutions.hotels.dto.Contacts;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateHotel {
    @NotBlank(message = "Name must not be blank")
    private String name;
    private String description;
    @NotBlank(message = "Brand must not be blank")
    private String brand;
    @Valid
    private Address address;
    @Valid
    private Contacts contacts;
    @Valid
    private ArrivalTime arrivalTime;

}
