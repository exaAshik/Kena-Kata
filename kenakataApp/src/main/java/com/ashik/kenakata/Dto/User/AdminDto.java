package com.ashik.kenakata.Dto.User;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.Instant;

@Data
public class AdminDto {

    private String username;

    private Instant lastLogin;

    private String phoneNumber;
}
