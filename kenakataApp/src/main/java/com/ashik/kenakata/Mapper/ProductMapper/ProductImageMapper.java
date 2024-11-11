package com.ashik.kenakata.Mapper.ProductMapper;

import com.ashik.kenakata.Dto.Product.ProductImageDto;
import com.ashik.kenakata.Entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductImageMapper {

    ProductImageMapper INSTANCE = Mappers.getMapper( ProductImageMapper.class );

    ProductImage productImageDtoToProductImage (ProductImageDto productImageDto);

    ProductImageDto productImageToProduct (ProductImage productImage);

}
