package com.gpsolutions.hotels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@NotNull(message = "Arrival time must not be null")
public class ArrivalTime {
    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Check-in time must not be null")
    private LocalTime checkIn;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime checkOut;
}
