package com.esprit.coworkingspaceback.roua.services;

import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IRecService {
     Reclamation addReclamation(Reclamation reclamation);
     List<Reclamation> getAllReclamations();
     void deleteReclamation(int reclamationId);
     Reclamation updateReclamationImage(int idEvent, MultipartFile recImage);
     String getImageUrlForReclamationByID(int idReclamation);
     Reclamation getReclamation(int id);
     void respondToReclamation(int id, String response) throws MessagingException;
     void sendEmail(String to, String subject, String body) throws MessagingException ;
}
