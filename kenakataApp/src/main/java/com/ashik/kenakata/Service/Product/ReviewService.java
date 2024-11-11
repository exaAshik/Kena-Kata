package com.ashik.kenakata.Service.Product;

import com.ashik.kenakata.Entity.Review;

import java.util.List;

public interface ReviewService {

    Review saveReview(Review review);

    Review getReviewById(Long id);

    List<Review> getAllReviews();

    List<Review> getReviewsByProductId(Long productId);

    void deleteReview(Long id);
}
