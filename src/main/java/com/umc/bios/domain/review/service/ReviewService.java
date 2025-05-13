package com.umc.bios.domain.review.service;

import com.umc.bios.domain.review.dto.request.ReviewRequestDto;
import com.umc.bios.domain.review.repository.ReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepositoryImpl reviewRepository;

    @Transactional
    public void createReview(ReviewRequestDto requestDto) {
        reviewRepository.insertReviewFromMemberId(
                requestDto.memberId(), requestDto.storeId(), requestDto.score(), requestDto.title()
        );
    }

}
