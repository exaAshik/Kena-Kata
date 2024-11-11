package com.ashik.kenakata.Service.Implementation.Product;

import com.ashik.kenakata.Entity.Review;
import com.ashik.kenakata.Exception.ResourceNotFoundException;
import com.ashik.kenakata.Repository.ReviewRepository;
import com.ashik.kenakata.Service.Product.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {

        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {

        return reviewRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found or has been deleted with id: " + id));
    }

    @Override
    public List<Review> getAllReviews() {

        return reviewRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {

        return reviewRepository.findByProductIdAndIsDeletedFalse(productId);
    }

    @Override
    public void deleteReview(Long id) {

        Review review = getReviewById(id);
        review.setDeleted(true);
        reviewRepository.save(review);
    }
}
