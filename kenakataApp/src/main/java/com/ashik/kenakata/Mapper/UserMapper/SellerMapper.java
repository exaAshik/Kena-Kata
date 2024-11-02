package com.ashik.kenakata.Mapper.UserMapper;

import com.ashik.kenakata.Dto.User.CustomerCreateDto;
import com.ashik.kenakata.Dto.User.SellerDto;
import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                SellerBankInfoMapper.class
        }
)
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper( SellerMapper.class );

    Seller sellerDtoToSeller(SellerDto sellerDto);

    SellerDto sellerToSellerDto(Seller seller);


}
