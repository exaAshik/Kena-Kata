package com.ashik.kenakata.Dto.Product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductImageDto {

    private Long id;

    private String imageUrl;
}
