package com.ashik.kenakata.Mapper.UserMapper;

import com.ashik.kenakata.Dto.User.CustomerCreateDto;
import com.ashik.kenakata.Dto.User.SellerBankAccountDto;
import com.ashik.kenakata.Entity.Customer;
import com.ashik.kenakata.Entity.SellerBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerBankInfoMapper {

    SellerBankInfoMapper INSTANCE = Mappers.getMapper( SellerBankInfoMapper.class );

    SellerBankAccount sellerBankAccountDtoToSellerBankAccount(SellerBankAccountDto sellerBankAccountDto);

    SellerBankAccountDto sellerBankAccountToSellerBankAccountDto(SellerBankAccount sellerBankAccount);
}
