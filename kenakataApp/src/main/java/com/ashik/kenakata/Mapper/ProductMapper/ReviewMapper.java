package com.ashik.kenakata.Mapper.ProductMapper;

import com.ashik.kenakata.Dto.Product.ReviewDto;
import com.ashik.kenakata.Entity.Review;
import com.ashik.kenakata.Mapper.UserMapper.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                CustomerMapper.class
        }
)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );

    ReviewDto reviewToReviewDto (Review review);

    Review reviewDtoToReview (ReviewDto reviewDto);

}
