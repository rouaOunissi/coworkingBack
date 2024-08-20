package com.esprit.coworkingspaceback.roua.services;

import com.esprit.coworkingspaceback.roua.email.EmailRecRes;
import com.esprit.coworkingspaceback.roua.entities.Reclamation;
import com.esprit.coworkingspaceback.roua.entities.Response;
import com.esprit.coworkingspaceback.roua.repo.RecRepository;
import com.esprit.coworkingspaceback.roua.repo.ResponseRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResponseService implements IResponseService{
    ResponseRepository responseRepository;
    RecRepository recRepository;
    EmailRecRes emailSender;




}
