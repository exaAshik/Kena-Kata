package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.User.CustomerCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/users")
public class UserController {


    @PostMapping
    public ResponseEntity<?> createUser (@RequestBody CustomerCreateDto customerCreateDto){

        return null;
    }
}
