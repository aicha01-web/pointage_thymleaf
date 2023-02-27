package com.groupeisi.pointage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessageToList(List<String> to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sokhnaaichasarr@gmail.com");
        for (String etudiant:to) {
            message.setTo(etudiant);
        }
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sokhnaaichasarr@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}