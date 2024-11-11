package com.ashik.kenakata.Mapper.Cart;

import com.ashik.kenakata.Dto.Cart.CartDto;
import com.ashik.kenakata.Entity.Cart;
import com.ashik.kenakata.Mapper.ProductMapper.ProductCategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper( CartMapper.class );

    CartDto cartToCartDto (Cart cart);

    Cart cartDtoToCart (CartDto cartDto);

}
