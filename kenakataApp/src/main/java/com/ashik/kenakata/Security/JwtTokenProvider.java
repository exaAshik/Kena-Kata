package com.ashik.kenakata.Security;

import com.ashik.kenakata.Utils.AppConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtTokenProvider {

    SecretKey secretKey = Keys.hmacShaKeyFor(AppConstant.JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public  String generateToken(Authentication authentication){

        String jwt = Jwts.builder().issuer("Kenakata Team").issuedAt(
                        new Date()
                ).expiration(new Date(new Date().getTime()+AppConstant.validityInMilliseconds))
                .claim("email",authentication.getName()).signWith(secretKey)
                .claim("role", authentication.getAuthorities())
                .compact();

        return jwt;

    }

    public String getEmailFromToken(String jwt){

        jwt = jwt.substring(7);

        Claims claim = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();

        return String.valueOf(claim.get("email"));
    }


}
