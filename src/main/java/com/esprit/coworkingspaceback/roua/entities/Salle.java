package com.esprit.coworkingspaceback.roua.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;

    private String description;
    private int nombrePlace;
    private double forfait;
    private String numSalle;

    @ManyToOne
    @JoinColumn(name = "idType")
    private TypeSalle typeSalle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
}
