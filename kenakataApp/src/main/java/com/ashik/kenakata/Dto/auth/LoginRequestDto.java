package com.ashik.kenakata.Dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotEmpty(message = "email cant be empty")
    private String email;

    @NotEmpty(message = "password cant be empty")
    private String password;
}
