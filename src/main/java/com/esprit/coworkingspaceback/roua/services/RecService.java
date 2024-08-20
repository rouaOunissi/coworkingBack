package com.esprit.coworkingspaceback.roua.services;

import com.esprit.coworkingspaceback.roua.email.EmailRecRes;
import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import com.esprit.coworkingspaceback.roua.repo.RecRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.esprit.coworkingspaceback.roua.utils.FileNamingUtil;
import com.esprit.coworkingspaceback.roua.utils.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecService implements IRecService {

    private final RecRepository recRepository;
    private final Environment environment;
    private final FileNamingUtil fileNamingUtil;
    private final FileUtil fileUtil;
    EmailRecRes emailSender;

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        System.out.println(reclamation.getUserReclamations());
        if (reclamation != null) {
            return recRepository.save(reclamation);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReclamation(int reclamationId) {
        // Check if the reclamation exists
        Optional<Reclamation> optionalReclamation = recRepository.findById(reclamationId);
        if (optionalReclamation.isPresent()) {
            recRepository.deleteById(reclamationId);
        } else {
            throw new IllegalArgumentException("Reclamation with ID " + reclamationId + " not found");
        }
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return recRepository.findAll();
    }

    @Override
    public Reclamation updateReclamationImage(int idReclamation, MultipartFile recImage) {
        Optional<Reclamation> optionalReclamation = recRepository.findById(idReclamation);
        if (optionalReclamation.isPresent()) {
            Reclamation reclamation = optionalReclamation.get();
            try {
                if (recImage != null && !recImage.isEmpty() && recImage.getSize() > 0) {
                    String uploadDir = environment.getProperty("upload.reclamation.images");
                    String newPhotoName = fileNamingUtil.nameFile(recImage);
                    reclamation.setRecImage(newPhotoName);
                    fileUtil.saveNewFile(uploadDir, newPhotoName, recImage);
                }
                return recRepository.save(reclamation);
            } catch (IOException e) {
                throw new RuntimeException("Failed to update reclamation image", e);
            }
        } else {
            throw new RuntimeException("Reclamation not found");
        }
    }

    @Override
    public String getImageUrlForReclamationByID(int idReclamation) {
        Reclamation reclamation=recRepository.findById(idReclamation).get();
        String baseUrl = environment.getProperty("export.reclamation.images");
        String recImage = reclamation.getRecImage();
        if (recImage != null && !recImage.isEmpty()) {
            return baseUrl + recImage;
        }
        return null;
    }

    public Reclamation getReclamation(int id) {
        return recRepository.findById(id).orElseThrow(() -> new RuntimeException("Reclamation not found"));
    }

    public void respondToReclamation(int id, String response) throws MessagingException {
        Reclamation reclamation = getReclamation(id);
        String userEmail = reclamation.getUserReclamations().getEmail();

        sendEmail(userEmail, "Response to your reclamation", response);
    }


    public void sendEmail(String to, String subject, String body) throws MessagingException {
        emailSender.sendSimpleMessage(to, subject, body);
    }
}
