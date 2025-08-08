package com.ensat.serviceimpl;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ensat.dto.EmailRequest;

import java.util.Optional;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendEmail(EmailRequest emailReq) throws Exception {
        Optional.ofNullable(emailReq)
                .ifPresentOrElse(req -> {
                    try {
                        MimeMessage message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true);

                        helper.setFrom(mailFrom, req.getTitle());
                        helper.setTo(req.getTo());
                        helper.setSubject(req.getSubject());
                        helper.setText(req.getMessage(), true);

                        mailSender.send(message);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
                    }
                }, 
                () -> {
                    throw new IllegalArgumentException("Email request cannot be null");
                });
    }
}