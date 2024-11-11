package com.ashik.kenakata.Service.Implementation;

import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Exception.ApiException;
import com.ashik.kenakata.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmailAndIsActiveTrueAndIsDeletedFalse(email).orElseThrow(
                ()-> new RuntimeException("User not found with email :"+email)
        );

        try {
            if(user == null){
                throw  new ApiException("Failed","user not found with email"+email);
            }

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().name());

            List<GrantedAuthority> authorityList = List.of(grantedAuthority);

            return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                    authorityList);

        } catch (ApiException e) {
            log.error("user not found with email"+email);
        }


        return null;
    }
}
