package com.esprit.coworkingspaceback.roua.email;

import jakarta.mail.MessagingException;

public interface EmailSender {
    public void send(String to , String email);
    public void sendSetPasswordEmail(String email)throws MessagingException;
}
