package com.esprit.coworkingspaceback.roua.controller;

import com.esprit.coworkingspaceback.roua.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthentificationResponse {
    private String token ;
    private Long idUser ;
    private Role role ;
}

