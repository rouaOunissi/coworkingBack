package com.esprit.coworkingspaceback.roua.services;

import com.esprit.coworkingspaceback.roua.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}
