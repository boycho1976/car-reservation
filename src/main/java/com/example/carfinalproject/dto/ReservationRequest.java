package com.example.carfinalproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationRequest {
    @NotBlank
    private Instant dateBegin;
    @NotBlank
    private int days;
    @NotBlank
    private long userId;
    @NotBlank
    private long carId;

}
