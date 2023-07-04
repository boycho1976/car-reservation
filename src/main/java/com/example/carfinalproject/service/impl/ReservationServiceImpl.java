package com.example.carfinalproject.service.impl;

import com.example.carfinalproject.converter.ReservationConverter;
import com.example.carfinalproject.dto.ReservationRequest;
import com.example.carfinalproject.dto.ReservationResponse;
import com.example.carfinalproject.dto.UpdateReservationRequest;
import com.example.carfinalproject.entity.Car;
import com.example.carfinalproject.entity.Reservation;
import com.example.carfinalproject.repository.CarRepository;
import com.example.carfinalproject.repository.ReservationRepository;
import com.example.carfinalproject.service.ReservationService;
import com.example.carfinalproject.utl.DateFormatterUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;
    private final CarRepository carRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.carRepository = carRepository;
    }

    @Override
    public Reservation bookReservation(ReservationRequest request) {
        return reservationRepository.save(reservationConverter.toReservation(request));
    }

    @Override
    public ReservationResponse findReservationById(long id) {
        return reservationConverter.toReservationResponse(reservationRepository.findById(id).get());
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Set<Reservation> findReservationByPeriodWithNative(Instant start, Instant end) {
        return reservationRepository.getReservationsByIntervalWithNative(
                        DateFormatterUtil.getDateFromDateTime(start).toString(),
                        DateFormatterUtil.getDateFromDateTime(end).toString())
                .orElse(Collections.emptySet());
    }

    @Override
    public ReservationResponse updateReservation(long id, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(id).get();
        if (request.getDateBegin() != null) {
            reservation.setDateBegin(request.getDateBegin());
            reservation.setDateEnd(reservationConverter.getDateEnd(reservation.getDays(), request.getDateBegin()));
        }
        if (request.getCarId() > 0) {
            Car car = carRepository.findById(request.getCarId()).get();
            reservation.setCar(car);
            reservation.setPrice(car.getPrice()*reservation.getDays());

        }
        if (request.getDays() > 0) {
            reservation.setDateEnd(reservationConverter.getDateEnd(request.getDays(), reservation.getDateBegin()));
            reservation.setPrice(reservation.getCar().getPrice()*request.getDays());
        }

        return reservationConverter.toReservationResponse(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationResponse> findByCar(long carId) {
        List<Reservation> reservations = reservationRepository.findByCar_Id(carId).get();

        return reservations.stream().map(reservation -> reservationConverter.toReservationResponse(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> findByUser(long userId) {
        List<Reservation> reservations = reservationRepository.findByUser_Id(userId).get();

        return reservations.stream().map(reservation -> reservationConverter.toReservationResponse(reservation))
                .collect(Collectors.toList());
    }

}
