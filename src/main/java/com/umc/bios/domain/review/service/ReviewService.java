package com.umc.bios.domain.review.service;

import com.umc.bios.domain.review.dto.request.ReviewRequestDto;
import com.umc.bios.domain.review.dto.response.ReviewResponseDto;
import com.umc.bios.domain.review.entity.Review;
import com.umc.bios.domain.review.repository.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepositoryImpl reviewRepository;

    @Transactional
    public ReviewResponseDto createReview(Long storeId, ReviewRequestDto.CreateDto requestDto) {
        Review newReview = reviewRepository.insertReviewFromMemberId(
                requestDto.getMemberId(), storeId, requestDto.getScore(), requestDto.getTitle()
        );
        return ReviewResponseDto.builder()
                .title(newReview.getTitle())
                .build();
    }

}
