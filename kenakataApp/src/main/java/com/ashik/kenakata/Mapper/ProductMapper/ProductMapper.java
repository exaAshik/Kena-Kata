package com.ashik.kenakata.Mapper.ProductMapper;

import com.ashik.kenakata.Dto.Product.ProductDto;
import com.ashik.kenakata.Entity.Product;
import com.ashik.kenakata.Mapper.UserMapper.SellerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
        ProductCategoryMapper.class,
        ReviewMapper.class,
        ProductImageMapper.class,
        SellerMapper.class
})
public interface ProductMapper {

    ProductCategoryMapper INSTANCE = Mappers.getMapper( ProductCategoryMapper.class );

    Product productDtoToProduct (ProductDto productDto);

    ProductDto productToProductDto (Product product);
}
