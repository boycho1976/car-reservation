package com.example.carfinalproject.converter;

import com.example.carfinalproject.dto.CarRequest;
import com.example.carfinalproject.dto.CarResponse;
import com.example.carfinalproject.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {
    public Car toCar(CarRequest request){
        return Car.builder()
                .seat(request.getSeat())
                .price(request.getPrice())
                .made(request.getMade())
                .model(request.getModel())
                .build();

    }
    public CarResponse toResponse(Car car){
        return CarResponse.builder()
                .made(car.getMade())
                .model(car.getModel())
                .price(car.getPrice())
                .seat(car.getSeat())
                .build();
    }
}
