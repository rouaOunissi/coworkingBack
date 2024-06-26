package com.esprit.coworkingspaceback.roua.controller;

import com.esprit.coworkingspaceback.roua.entities.User;
import com.esprit.coworkingspaceback.roua.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService ;

    @GetMapping
    public List<User> findAll(){
        return this.userService.findAll();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("level") int level,
            @RequestParam("numTel") int numTel
    ) throws IOException {
        User user = userService.getUserById(id);


        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setNumTel(numTel);



        User updatedUser = userService.updateUser(id, firstName,lastName,email,password,numTel);
        return ResponseEntity.ok(updatedUser);


    }



    @GetMapping("/user/{idd}")
    public User getUserById(@PathVariable Long idd) {
        return userService.getUserById(idd);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String firstName) {
        return userService.searchByFirstName(firstName);
    }




}
