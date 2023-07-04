package com.example.carfinalproject.controller;

import com.example.carfinalproject.converter.ReservationConverter;
import com.example.carfinalproject.dto.*;
import com.example.carfinalproject.entity.Reservation;
import com.example.carfinalproject.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
     private final ReservationService reservationService;
     private final ReservationConverter reservationConverter;
    @Autowired
    public ReservationController(ReservationService reservationService, ReservationConverter reservationConverter) {
        this.reservationService = reservationService;
        this.reservationConverter = reservationConverter;
    }
    @PostMapping
    public ResponseEntity<Reservation> register(@RequestBody ReservationRequest request){
         return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.bookReservation(request));
     }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(reservationService.findReservationById(id));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Reservation with Id "+id+" deleted.");
    }
    @GetMapping(path = "/period")
    public ResponseEntity<Set<ReservationResponse>> getReservationByPeriod(@RequestParam("start") Instant start,
                                                                           @RequestParam("end")Instant end){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByPeriodWithNative(start,end).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateUser( @PathVariable Long id,
                                                   @RequestBody UpdateReservationRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reservationService.updateReservation(id,request));
    }

    @GetMapping("/car")
    public ResponseEntity<List<ReservationResponse>> findReservationByCar(
            @RequestParam long carId) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(reservationService.findByCar(carId));
    }
    @GetMapping("/user")
    public ResponseEntity<List<ReservationResponse>> findReservationByUser(
            @RequestParam long userId) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(reservationService.findByUser(userId));
    }


}
