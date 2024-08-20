package com.esprit.coworkingspaceback.roua.controller;

import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import com.esprit.coworkingspaceback.roua.services.IRecService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.List;

@Configuration
@EnableWebMvc
@RestController
@AllArgsConstructor
@RequestMapping("/Reclamation")
@CrossOrigin(origins = "http://localhost:4200")
public class RecRestController {
    IRecService iRecService;
    @PostMapping("/addRec")
    public Reclamation addReclamation(@RequestBody Reclamation reclamation) {
        return iRecService.addReclamation(reclamation);
    }

    @DeleteMapping("/deleteRec/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable("id") int reclamationId) {
        iRecService.deleteReclamation(reclamationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allRecs")
    public List<Reclamation> getAllReclamations() {
        return iRecService.getAllReclamations();
    }

    @PostMapping("/updateImage/{id}")
    public ResponseEntity<Reclamation> updateReclamationImage(
            @PathVariable("id") int idReclamation,
            @RequestParam("recImage") MultipartFile recImage
    ) {
        try {
            Reclamation updatedReclamation = iRecService.updateReclamationImage(idReclamation, recImage);
            return ResponseEntity.ok(updatedReclamation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getImageUrl/{id}")
    public String getImageUrlForReclamationByID(@PathVariable("id") int idReclamation) {
        return iRecService.getImageUrlForReclamationByID(idReclamation);
    }

    @GetMapping("/GetRec/{id}")
    public Reclamation getReclamation(@PathVariable int id) {
        return iRecService.getReclamation(id);
    }

    // Endpoint to respond to a reclamation
    @PostMapping("/respond/{id}")
    public void respondToReclamation(@PathVariable int id, @RequestParam String response) throws MessagingException {
        iRecService.respondToReclamation(id, response);
    }
}
