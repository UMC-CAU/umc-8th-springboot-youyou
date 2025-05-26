package com.umc.bios.domain.review.repository;

public interface ReviewRepositoryCustom {
    void insertReviewFromMemberId(Long memberId, Long storeId, Float score, String title);
}