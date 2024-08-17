package com.esprit.coworkingspaceback.roua.repo;

import com.esprit.coworkingspaceback.roua.entities.Reservation;
import com.esprit.coworkingspaceback.roua.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySalleAndDateDebutBetweenOrDateFinBetween(Salle salle, LocalDateTime start, LocalDateTime end, LocalDateTime startEnd, LocalDateTime endEnd);}


