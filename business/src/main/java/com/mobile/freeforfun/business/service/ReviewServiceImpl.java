package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.mapper.ReviewMapper;
import com.mobile.freeforfun.persistence.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	private ReviewMapper reviewMapper;

	@Autowired
	public ReviewServiceImpl(
			ReviewRepository reviewRepository,
			ReviewMapper reviewMapper) {
		this.reviewRepository = reviewRepository;
		this.reviewMapper = reviewMapper;
	}
}
