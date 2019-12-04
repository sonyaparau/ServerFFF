package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.ReviewDto;
import com.mobile.freeforfun.persistence.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapperImpl implements ReviewMapper {

	private UserMapper userMapper;
	private LocalMapper localMapper;

	@Autowired
	public ReviewMapperImpl(UserMapper userMapper, LocalMapper localMapper) {
		this.userMapper = userMapper;
		this.localMapper = localMapper;
	}

	@Override public ReviewDto toDto(Review review) {
		return ReviewDto.builder()
				.id(review.getId())
				.user(userMapper.toDto(review.getUser()))
				.local(localMapper.toDto(review.getLocal()))
				.description(review.getDescription())
				.build();
	}

	@Override public Review toEntity(ReviewDto reviewDto) {
		return Review.builder()
				.id(reviewDto.getId())
				.user(userMapper.toEntity(reviewDto.getUser()))
				.local(localMapper.toEntity(reviewDto.getLocal()))
				.description(reviewDto.getDescription())
				.build();
	}

	@Override public List<ReviewDto> toDtoList(List<Review> allReviews) {
		List<ReviewDto> reviewDtos = new ArrayList<>();
		allReviews.forEach(review ->
				reviewDtos.add(toDto(review)));
		return reviewDtos;
	}

	@Override public List<Review> toEntityList(List<ReviewDto> allReviewDtos) {
		List<Review> reviews = new ArrayList<>();
		allReviewDtos.forEach(reviewDto ->
				reviews.add(toEntity(reviewDto)));
		return reviews;
	}
}
