package com.ashik.kenakata.Dto.Product;

import com.ashik.kenakata.Dto.User.CustomerDto;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    private int rating;

    private String reviewContent;

    private String imageLink;

    private CustomerDto customer;
}
