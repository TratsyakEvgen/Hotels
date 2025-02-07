package com.gpsolutions.hotels.dto.request;

import com.gpsolutions.hotels.dto.Address;
import com.gpsolutions.hotels.dto.ArrivalTime;
import com.gpsolutions.hotels.dto.Contacts;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    private Address address;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    private Contacts contacts;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    private ArrivalTime arrivalTime;

}
