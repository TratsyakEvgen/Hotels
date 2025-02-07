package com.gpsolutions.hotels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@NotNull(message = "Arrival time must not be null")
public class ArrivalTime {
    @Schema(implementation = String.class, example = "12:00")
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Check-in time must not be null")
    private LocalTime checkIn;
    @Schema(implementation = String.class, example = "12:00")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
