package com.ashik.kenakata.Service.Implementation.User;

import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Repository.UserRepository;
import com.ashik.kenakata.Service.User.UserService;
import com.ashik.kenakata.Utils.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {

        return switch (user.getRole()) {
            case ADMIN -> createAdmin(user);
            case SELLER -> createSeller(user);
            default -> createCustomer(user);
        };
    }

    private User createCustomer(User user) {

        if(user != null && isValidCustomerData(user)){

            return userRepository.save(user);
        }
        return null;
    }

    private User createSeller(User user) {

        if(user != null && isValidSellerData(user)){

            return userRepository.save(user);
        }
        return null;
    }

    private User createAdmin(User user) {
        if(user != null && isValidSellerData(user)){

            return userRepository.save(user);
        }
        return null;
    }

    private boolean isValidCustomerData(User user){

        //TODO Add validation logic here
        return true;
    }

    private boolean isValidSellerData(User user){

        //TODO Add validation logic here
        return true;
    }

    private boolean isValidAdminData(User user){

        //TODO Add validation logic here
        return true;
    }
}
