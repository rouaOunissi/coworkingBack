package com.esprit.coworkingspaceback.roua.repo;

import com.esprit.coworkingspaceback.roua.entities.Role;
import com.esprit.coworkingspaceback.roua.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User , Long> {
    Optional<User> findByEmail(String email);


    User findByRole(Role role);
    List<User> findByFirstNameContaining(String firstName);

    @Query("SELECT u.email FROM User u WHERE u.idUser = :id")
    Optional<String> findEmailById(@Param("id") Long id);
}
