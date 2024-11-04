package com.ashik.kenakata.Mapper.ProductMapper;

import com.ashik.kenakata.Dto.Product.ProductCategoryDto;
import com.ashik.kenakata.Entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCategoryMapper {

    ProductCategoryMapper INSTANCE = Mappers.getMapper( ProductCategoryMapper.class );

    ProductCategory productCategoryDtoToProductCategory (ProductCategoryDto productCategoryDto);

    ProductCategoryDto productCategoryToProductCategoryDto (ProductCategory productCategory);

}
