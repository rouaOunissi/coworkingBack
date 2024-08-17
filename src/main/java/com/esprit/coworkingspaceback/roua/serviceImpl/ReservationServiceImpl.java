package com.esprit.coworkingspaceback.roua.serviceImpl;

import com.esprit.coworkingspaceback.roua.entities.Reservation;
import com.esprit.coworkingspaceback.roua.entities.Salle;
import com.esprit.coworkingspaceback.roua.repo.ReservationRepository;
import com.esprit.coworkingspaceback.roua.repo.SalleRepository;
import com.esprit.coworkingspaceback.roua.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        // Check Salle availability
        Salle salle = salleRepository.findById(reservation.getSalle().getIdSalle())
                .orElseThrow(() -> new IllegalArgumentException("Salle not found"));

        LocalDateTime dateDebut = reservation.getDateDebut();
        LocalDateTime dateFin = reservation.getDateFin();

        List<Reservation> overlappingReservations = reservationRepository.findBySalleAndDateDebutBetweenOrDateFinBetween(
                salle, dateDebut, dateFin, dateDebut, dateFin);

        if (!overlappingReservations.isEmpty()) {
            throw new IllegalArgumentException("Salle is not available for the selected time period");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    }
    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        existingReservation.setDateDebut(reservation.getDateDebut());
        existingReservation.setDateFin(reservation.getDateFin());
        existingReservation.setDuree(reservation.getDuree());
        existingReservation.setSalle(reservation.getSalle());

        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservationRepository.delete(reservation);
    }
}
