package com.ashik.kenakata.Service;


import com.ashik.kenakata.Dto.Email.EmailDetailsDto;

public interface EmailService {

    String sendSimpleMail(EmailDetailsDto details);

    String sendMailWithAttachment(EmailDetailsDto details);
}
