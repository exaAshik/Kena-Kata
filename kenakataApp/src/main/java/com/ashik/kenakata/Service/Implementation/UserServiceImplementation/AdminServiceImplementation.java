package com.ashik.kenakata.Service.Implementation.UserServiceImplementation;

import com.ashik.kenakata.Entity.Admin;
import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.UserRepository;
import com.ashik.kenakata.Service.User.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Admin saveAdmin(Admin admin) {

        return userRepository.save(admin);
    }

    @Override
    public Admin findAdminById(Long id) {

        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(id);

        return user.filter(u -> u instanceof Admin)
                .map(u -> (Admin) u)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + id));
    }

    @Override
    public List<Admin> findAllAdmins() {

        List<User> users = userRepository.findAllByIsDeletedFalse();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No active admins found.");
        }
        return users.stream()
                .filter(user -> user instanceof Admin)
                .map(user -> (Admin) user)
                .collect(Collectors.toList());
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with id " + id + " not found or is deleted."));

        if (!(existingUser instanceof Admin)) {
            throw new ResourceNotFoundException("User with id " + id + " is not an admin.");
        }

        Admin existingAdmin = (Admin) existingUser;

        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setLastLogin(admin.getLastLogin());
        existingAdmin.setPhoneNumber(admin.getPhoneNumber());

        return userRepository.save(existingAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {

        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with id " + id + " not found or is already deleted."));

        if (!(existingUser instanceof Admin)) {
            throw new ResourceNotFoundException("User with id " + id + " is not an admin.");
        }

        existingUser.setDeleted(true);
        userRepository.save(existingUser);

    }
}
