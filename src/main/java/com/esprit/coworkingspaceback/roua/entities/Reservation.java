package com.esprit.coworkingspaceback.roua.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idSalle")
    private Salle salle;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int duree;
}
