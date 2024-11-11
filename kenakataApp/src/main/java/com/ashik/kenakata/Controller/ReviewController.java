package com.ashik.kenakata.Controller;

import com.ashik.kenakata.Dto.Product.ReviewDto;
import com.ashik.kenakata.Entity.Review;
import com.ashik.kenakata.Mapper.ProductMapper.ReviewMapper;
import com.ashik.kenakata.Service.Product.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto) {

        ReviewDto review = ReviewMapper.INSTANCE.reviewToReviewDto(reviewService.saveReview(
                        ReviewMapper.INSTANCE.reviewDtoToReview(reviewDto)
                )
        );

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {

        ReviewDto review = ReviewMapper.INSTANCE.reviewToReviewDto(reviewService.getReviewById(id));

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
