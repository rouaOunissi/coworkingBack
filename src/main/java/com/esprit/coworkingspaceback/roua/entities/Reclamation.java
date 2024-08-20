package com.esprit.coworkingspaceback.roua.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reclamationID;
    String reclamationTitle;
    String reclamationDescription;
    @Enumerated(EnumType.STRING)
    private Category category;
    String recImage;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reclamationDate;
    //@ManyToOne(cascade = CascadeType.ALL)
    //private User userRec;
    @ManyToOne
    //@JoinColumn(name = "id_user", nullable = false)
    private User userReclamations;



}
