package com.example.carfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String made;
    private String model;
    private int seat;
    private double price;
    @OneToMany(mappedBy = "car" , fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservation> reservation ;
}
