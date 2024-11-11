package com.ashik.kenakata.Security;

import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Repository.UserRepository;
import com.ashik.kenakata.Utils.AppConstant;
import com.ashik.kenakata.Utils.enums.RoleType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
@Slf4j
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;


    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        try {
            OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
            String email = oauthUser.getAttribute("email");

            User user = findOrCreateUser(oauthUser);

            String jwtToken = createAndAuthenticateUser(email);

            sendJwtTokenInResponse(response, jwtToken);

        } catch (Exception ex) {
            log.error("Authentication success handling failed", ex);
        }

    }

    private User findOrCreateUser(OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String imageUrl = oauthUser.getAttribute("picture");

        User user = userRepository.findByEmail(email);
        if (user != null) {
            if (!user.isAuth2Login()) {
                throw new RuntimeException("User already registered without Google login");
            }
            return user;
        }

        return registerNewUser(email, name, imageUrl);
    }

    private User registerNewUser(String email, String name, String imageUrl) {
        User customer = Customer.builder()
                .profileImageUrl(imageUrl)
                .build();
        customer.setEmail(email);
        customer.setUserName(name);
        customer.setRole(RoleType.CUSTOMER);
        customer.setActive(false);
        customer.setAuth2Login(true);
        customer.setPassword(AppConstant.SSO_DEFAULT_PASSWORD);

        return userRepository.save(customer);
    }

    private String createAndAuthenticateUser(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        return jwtTokenProvider.generateToken(authenticationToken);
    }

    private void sendJwtTokenInResponse(HttpServletResponse response, String jwtToken) throws IOException {
        response.addHeader("Authorization", "Bearer " + jwtToken);
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + jwtToken + "\"}");
        response.getWriter().flush();
    }


}
