package com.example.carfinalproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {
    @NotBlank
    @Length(min = 2,message = "Name should contain at least 1 characters")
    private String made;
    @NotBlank
    @Length(min = 1,message = "Name should contain at least 1 characters")
    private String model;

    @NotBlank
    private int seat;
    @NotBlank
    private double price ;
}
