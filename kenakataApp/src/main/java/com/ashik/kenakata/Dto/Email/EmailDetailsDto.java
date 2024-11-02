package com.ashik.kenakata.Dto.Email;

import lombok.Data;

@Data
public class EmailDetailsDto {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
