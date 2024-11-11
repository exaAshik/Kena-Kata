package com.ashik.kenakata.Dto.Product;

import com.ashik.kenakata.Dto.User.SellerDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private String name;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private Integer totalSale;

    private Integer discountPercentage;

    private ProductCategoryDto category;

    private List<ReviewDto> reviews;

    private List<ProductImageDto> images;

    private SellerDto seller;
}
