package com.ashik.kenakata.Dto.Cart;

import com.ashik.kenakata.Dto.Product.ProductDto;
import com.ashik.kenakata.Entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CartItemDto {

    private ProductDto product;

    private Integer quantity;

    private Double price;
}
