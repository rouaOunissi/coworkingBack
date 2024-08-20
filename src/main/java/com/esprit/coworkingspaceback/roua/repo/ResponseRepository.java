package com.esprit.coworkingspaceback.roua.repo;

import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import com.esprit.coworkingspaceback.roua.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response,Long> {
}
