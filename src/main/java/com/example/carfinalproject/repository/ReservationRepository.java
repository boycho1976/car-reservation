package com.example.carfinalproject.repository;

import com.example.carfinalproject.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>  {

    @Query(nativeQuery = true, value = "SELECT * FROM reservations WHERE date_begin between ?1 and ?2 AND date_end between ?1 and ?2")
    Optional<Set<Reservation>> getReservationsByIntervalWithNative(String start, String end);

    Optional<List<Reservation>> findByCar_Id(long id);
    Optional<List<Reservation>> findByUser_Id(long id);
}
