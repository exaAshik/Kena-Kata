package com.ashik.kenakata.Dto.order;

import com.ashik.kenakata.Dto.Product.ProductDto;
import lombok.Data;

@Data
public class OrderItemDto {

    private ProductDto product;

    private Integer quantity;

    private Double price;

    private Double discount;
}
