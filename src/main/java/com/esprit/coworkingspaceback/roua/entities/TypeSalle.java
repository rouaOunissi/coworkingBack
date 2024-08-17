package com.esprit.coworkingspaceback.roua.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TypeSalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;

    private String type;

    @OneToMany(mappedBy = "typeSalle", cascade = CascadeType.ALL)
    private Set<Salle> salles;
}
