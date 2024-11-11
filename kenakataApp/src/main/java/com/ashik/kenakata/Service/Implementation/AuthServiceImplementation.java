package com.ashik.kenakata.Service.Implementation;

import com.ashik.kenakata.Security.JwtTokenProvider;
import com.ashik.kenakata.Service.AuthService;
import com.ashik.kenakata.Utils.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authentication(String email, String password) {

        log.info("start authenticate :"+email);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw  new BadCredentialsException("Invalid userName ...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        UsernamePasswordAuthenticationToken authenticationuser = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationuser);
        String jwt = jwtTokenProvider.generateToken(authenticationuser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(jwt);
        loginResponse.setRefreshToken("refresh token ");

        return loginResponse;
    }
}
