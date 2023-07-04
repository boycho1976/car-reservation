package com.example.carfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateReservationRequest {
    private long carId;
    private Instant dateBegin;
    private int days;
}
