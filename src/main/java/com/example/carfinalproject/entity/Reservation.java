package com.example.carfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Entity(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Instant dateBegin;
    private Instant dateEnd;
    private double price;
    private int days;
    @ManyToOne
    @JsonManagedReference
    private User user;
    @ManyToOne
    @JsonManagedReference
    private Car car;

}
