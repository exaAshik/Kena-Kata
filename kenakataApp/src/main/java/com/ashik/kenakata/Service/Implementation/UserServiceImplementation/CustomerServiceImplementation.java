package com.ashik.kenakata.Service.Implementation.UserServiceImplementation;

import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.UserRepository;
import com.ashik.kenakata.Service.User.CustomerService;
import com.ashik.kenakata.Utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer saveCustomer(Customer customer) {

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(RoleType.CUSTOMER);
        customer.setAuth2Login(false);

        return userRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {

        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(id);

        return user.filter(u -> u instanceof Customer)
                .map(u -> (Customer) u)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

    }

    @Override
    public List<Customer> findAllCustomers() {

        List<User> users = userRepository.findAllByIsDeletedFalse();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No active users found.");
        }

        return users.stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .toList();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found or is deleted."));

        if (!(existingUser instanceof Customer)) {
            throw new ResourceNotFoundException("User with id " + id + " is not a customer.");
        }

        Customer existingCustomer = (Customer) existingUser;

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setPoints(customer.getPoints());
        existingCustomer.setProfileImageUrl(customer.getProfileImageUrl());
        existingCustomer.setSubscription(customer.getSubscription());

        return userRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found or is already deleted."));

        if (!(existingUser instanceof Customer)) {
            throw new ResourceNotFoundException("User with id " + id + " is not a customer.");
        }

        existingUser.setDeleted(true);

        userRepository.save(existingUser);

    }
}
