package com.example.carfinalproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarResponse {
    private String made;
    private String model;
    private int seat;
    private double price;
}
