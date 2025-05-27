package com.umc.bios.domain.review.service;

import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.repository.MemberRepository;
import com.umc.bios.domain.review.dto.request.ReviewRequestDto;
import com.umc.bios.domain.review.dto.response.ReviewResponseDto;
import com.umc.bios.domain.review.entity.Review;
import com.umc.bios.domain.review.repository.ReviewRepository;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResponseDto createReview(Long storeId, ReviewRequestDto.CreateDto requestDto) {
        Review newReview = reviewRepository.insertReviewFromMemberId(
                requestDto.getMemberId(), storeId, requestDto.getScore(), requestDto.getTitle()
        );
        return ReviewResponseDto.builder()
                .title(newReview.getTitle())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponseDto> readAll(Long memberId, int page, int size) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, size);

        return reviewRepository.findAllByMemberId(pageable, memberId)
                .map(Review::toResponseDto);
    }
}
