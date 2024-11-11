package com.ashik.kenakata.Repository;

import com.ashik.kenakata.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Optional<Review> findByIdAndIsDeletedFalse(Long id);

    List<Review> findByProductIdAndIsDeletedFalse(Long productId);

    List<Review> findAllByIsDeletedFalse();

}
