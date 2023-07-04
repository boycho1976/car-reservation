package com.example.carfinalproject.service;

import com.example.carfinalproject.dto.CarRequest;
import com.example.carfinalproject.dto.CarResponse;
import com.example.carfinalproject.dto.UpdateCarRequest;
import com.example.carfinalproject.entity.Car;

public interface CarService {
    CarResponse addCar(CarRequest request);
    CarResponse getCar(Long id);
    String deleteCar(Long id);
    CarResponse updateCar(long id,UpdateCarRequest request);
}
