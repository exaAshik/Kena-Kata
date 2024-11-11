package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
