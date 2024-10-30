package com.ashik.kenakata.Mapper.UserMapper;

import com.ashik.kenakata.Dto.User.CustomerCreateDto;
import com.ashik.kenakata.Entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                SubscriptionMapper.class
        }
)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    Customer customerCreateDtoToCustomer(CustomerCreateDto customerCreateDto);

}
