package com.esprit.coworkingspaceback.roua.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String context;
     Date responseDate;






}
