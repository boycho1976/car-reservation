package com.example.carfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.ast.SqlTreeCreationException;

import java.util.Date;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private Date registerDate;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservation> reservation;
}
