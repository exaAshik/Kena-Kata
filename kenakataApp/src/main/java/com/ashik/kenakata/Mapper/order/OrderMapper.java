package com.ashik.kenakata.Mapper.order;

import com.ashik.kenakata.Dto.order.OrderDto;
import com.ashik.kenakata.Entity.Order;
import com.ashik.kenakata.Mapper.Cart.CartMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

    Order orderDtoToOrder (OrderDto orderDto);

    OrderDto orderToOrderDto (Order order);

}
