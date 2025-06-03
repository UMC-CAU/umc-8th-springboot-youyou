package com.umc.bios.domain.review.repository;

import com.umc.bios.domain.review.entity.Review;

public interface ReviewRepositoryCustom {
    Review insertReviewFromMemberId(Long memberId, Long storeId, Float score, String title);
}