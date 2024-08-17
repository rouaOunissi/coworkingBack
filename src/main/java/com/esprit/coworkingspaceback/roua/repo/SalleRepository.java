package com.esprit.coworkingspaceback.roua.repo;

import com.esprit.coworkingspaceback.roua.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
}

