package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.ReviewDto;
import com.mobile.freeforfun.persistence.model.Review;

import java.util.List;

public interface ReviewMapper {
	ReviewDto toDto(Review review);
	Review toEntity(ReviewDto reviewDto);
	List<ReviewDto> toDtoList(List<Review> allReviews);
	List<Review> toEntityList(List<ReviewDto> allReviewDtos);
}
