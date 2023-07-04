package com.example.carfinalproject.converter;

import com.example.carfinalproject.dto.ReservationRequest;
import com.example.carfinalproject.dto.ReservationResponse;
import com.example.carfinalproject.entity.Car;
import com.example.carfinalproject.entity.Reservation;
import com.example.carfinalproject.entity.User;
import com.example.carfinalproject.repository.CarRepository;
import com.example.carfinalproject.repository.UserRepository;
import com.example.carfinalproject.utl.DateFormatterUtil;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ReservationConverter {
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public ReservationConverter(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }
        public Reservation toReservation(ReservationRequest request){
        User user = userRepository.findById(request.getUserId()).get();
        Car car = carRepository.findById(request.getCarId()).get();
        return Reservation.builder()
                .car(car)
                .user(user)
                .days(request.getDays())
                .dateBegin(request.getDateBegin())
                .price(car.getPrice()*request.getDays())
                .dateEnd(getDateEnd(request.getDays(), request.getDateBegin()))
                .build();
    }
    public ReservationResponse toReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .user(reservation.getUser())
                .car(reservation.getCar())
                .price(reservation.getPrice())
                .dateBegin(DateFormatterUtil.getDateFromDateTime(reservation.getDateBegin()).toString())
                .dateEnd(DateFormatterUtil.getDateFromDateTime(reservation.getDateEnd()).toString())
                .id(reservation.getId())
                .build();
    }
    public Instant getDateEnd(int days, Instant dateBegin) {
        return dateBegin.plusMillis
                (TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS));
    }
}
