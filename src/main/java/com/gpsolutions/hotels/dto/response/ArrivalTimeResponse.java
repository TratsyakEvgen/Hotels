package com.gpsolutions.hotels.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ArrivalTimeResponse {
    private LocalTime checkIn;
    private LocalTime checkOut;
}
