package com.ashik.kenakata.Utils.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String jwtToken;

    private String refreshToken;
}
