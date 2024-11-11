package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.auth.LoginRequestDto;
import com.ashik.kenakata.Service.AuthService;
import com.ashik.kenakata.Utils.response.LoginResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Valid LoginRequestDto loginRequestDto){

        LoginResponse authentication = authService.authentication(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }

}
