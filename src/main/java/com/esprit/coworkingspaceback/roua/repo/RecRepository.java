package com.esprit.coworkingspaceback.roua.repo;

import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecRepository extends JpaRepository<Reclamation,Integer> {
}
