package com.stocks.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}") private String sender;
    
    public String sendSimpleMail(EmailDetails emailDetails) {
        //Preparing to send message.
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            //Set up the necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());
            jms.send(mailMessage);
            return "Mail sent succesfully to: " + emailDetails.getRecipient();
        } catch (Exception e) {
            return "Error whil sending mail";
        }
    }
}
