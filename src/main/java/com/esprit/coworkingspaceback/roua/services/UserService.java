package com.esprit.coworkingspaceback.roua.services;

import com.esprit.coworkingspaceback.roua.entities.User;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {




        User updateUser(Long id,
                        @RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,

                        @RequestParam("numTel") int numTel) throws IOException;

        void deleteUser(Long id);

        User getUserById(Long id);

        List<User> findAll();

        List<User> searchByFirstName(String firstName);
    public Optional<String> findEmailById(Long id);

    public Boolean checkUserEnabled(String email);


    String forgotPassword(String email) throws MessagingException;

    String setPassword(String email, String newPassword);
    public List<Object[]> getUsersRegistrationStats();



}

