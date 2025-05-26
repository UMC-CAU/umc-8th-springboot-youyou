package com.umc.bios.domain.review.dto.request;

import lombok.Getter;

public record ReviewRequestDto (
){
    @Getter
    public static class CreateDto {
        private String title;
        private Float score;
        private Long memberId;
    }
}
