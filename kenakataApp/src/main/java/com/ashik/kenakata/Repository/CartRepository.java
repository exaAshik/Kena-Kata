package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByCustomerId(Long customerId);

}
