package com.ashik.kenakata.Service.Implementation.UserServiceImplementation;

import com.ashik.kenakata.Entity.Seller;
import com.ashik.kenakata.Entity.User;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.UserRepository;
import com.ashik.kenakata.Service.User.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImplementation implements SellerService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Seller saveSeller(Seller seller) {

        return userRepository.save(seller);
    }

    @Override
    public Seller findSellerById(Long id) {
        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(id);

        return user.filter(u -> u instanceof Seller)
                .map(u -> (Seller) u)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id " + id));
    }

    @Override
    public List<Seller> findAllSellers() {

        List<User> users = userRepository.findAllByIsDeletedFalse();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No active users found.");
        }

        return users.stream()
                .filter(user -> user instanceof Seller)
                .map(user -> (Seller) user)
                .toList();
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) {

        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller with id " + id + " not found or is deleted."));

        if (!(existingUser instanceof Seller)) {
            throw new ResourceNotFoundException("User with id " + id + " is not a seller.");
        }

        Seller existingSeller = (Seller) existingUser;

        existingSeller.setName(seller.getName());
        existingSeller.setPhone(seller.getPhone());
        existingSeller.setAddress(seller.getAddress());
        existingSeller.setCompanyName(seller.getCompanyName());
        existingSeller.setLogoUrl(seller.getLogoUrl());
        existingSeller.setCompanyRegistrationNumber(seller.getCompanyRegistrationNumber());
        existingSeller.setProducts(seller.getProducts());
        existingSeller.setBankAccounts(seller.getBankAccounts());

        return userRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) {

        User existingUser = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller with id " + id + " not found or is already deleted."));

        if (!(existingUser instanceof Seller)) {
            throw new ResourceNotFoundException("User with id " + id + " is not a seller.");
        }

        existingUser.setDeleted(true);
        userRepository.save(existingUser);

    }
}
