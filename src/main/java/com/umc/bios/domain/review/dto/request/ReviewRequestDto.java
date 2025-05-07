package com.umc.bios.domain.review.dto.request;

public record ReviewRequestDto (
        Long memberId,
        Long storeId,
        Float score,
        String title
){
}
