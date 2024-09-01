package com.example.ecommerce_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailService {

    private static final Logger logger = Logger.getLogger(EmailService.class.getName());

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String toEmail, String resetPasswordLink) {
        if (toEmail == null || toEmail.isEmpty()) {
            logger.warning("Email address is null or empty. Cannot send reset password email.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Reset your password");
        message.setText("To reset your password, click the link below:\n" + resetPasswordLink);

        try {
            mailSender.send(message);
            logger.info("Reset password email sent successfully to " + toEmail);
        } catch (Exception e) {
            logger.severe("Failed to send reset password email to " + toEmail + ". Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
