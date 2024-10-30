package com.ashik.kenakata.Mapper.UserMapper;

import com.ashik.kenakata.Dto.User.SubscriptionDto;
import com.ashik.kenakata.Entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper( SubscriptionMapper.class );

    SubscriptionDto subscriptionTOSubscriptionDto(Subscription subscription);

    Subscription subscriptionDtoTOSubscription(SubscriptionDto subscriptionDto);
}
