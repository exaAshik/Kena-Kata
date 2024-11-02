package com.ashik.kenakata.Service.Implementation;


import com.ashik.kenakata.Dto.Email.EmailDetailsDto;
import com.ashik.kenakata.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplementation implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetailsDto details) {

        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            mailSender.send(mailMessage);

            return "Mail Sent Successfully...";
        }

        catch (Exception e) {

            return "Error while Sending Mail "+e.getMessage();
        }
    }


    @Override
    public String sendMailWithAttachment(EmailDetailsDto details) {
        return "";
    }
}
