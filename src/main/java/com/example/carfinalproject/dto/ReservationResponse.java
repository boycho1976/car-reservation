package com.example.carfinalproject.dto;

import com.example.carfinalproject.entity.Car;
import com.example.carfinalproject.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {
    private Long id;
    private String dateBegin;
    private String dateEnd;
    private double price;
    private Car car;
    private User user;
}