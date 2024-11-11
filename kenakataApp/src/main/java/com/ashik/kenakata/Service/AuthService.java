package com.ashik.kenakata.Service;

import com.ashik.kenakata.Utils.response.LoginResponse;

public interface AuthService {

     LoginResponse authentication(String email, String password);
}
