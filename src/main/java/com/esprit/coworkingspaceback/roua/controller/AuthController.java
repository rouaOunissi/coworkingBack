package com.esprit.coworkingspaceback.roua.controller;


import com.esprit.coworkingspaceback.roua.entities.User;
import com.esprit.coworkingspaceback.roua.repo.UserRepo;
import com.esprit.coworkingspaceback.roua.serviceImpl.AuthService;
import com.esprit.coworkingspaceback.roua.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService ;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo ;


    @PostMapping("/authentificate")
    public ResponseEntity<AuthentificationResponse> authentificate(@RequestBody AuthentificationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("password") String password,
            @RequestParam("numTel") int numTel

    ) throws IOException {
        Optional<User> existingUser = authService.findByEmail(email);
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setNumTel(numTel);



        authService.register(email, firstName, lastName, password, numTel);

        return ResponseEntity.ok(new ApiResponse("Registration successful"));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgitPassword(@RequestParam String email) throws MessagingException {
        return new ResponseEntity<>(userService.forgotPassword(email),HttpStatus.OK) ;
    }

    @PutMapping("/set-password")
    public ResponseEntity<Map<String, String>> setPassword(@RequestParam String email, @RequestBody PasswordDto passwordDto){
        String message = userService.setPassword(email, passwordDto.getNewPassword());
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
