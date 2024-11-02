package com.ashik.kenakata.Mapper.UserMapper;

import com.ashik.kenakata.Dto.User.AdminDto;
import com.ashik.kenakata.Entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper( AdminMapper.class );

    Admin adminDtoToadmin (AdminDto adminDto);

    AdminDto adminToAdminDto (Admin admin);


}
