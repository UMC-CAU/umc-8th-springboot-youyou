package com.umc.bios.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record ReviewRequestDto (
){
    @Getter
    @Schema(name = "특정 가게에 리뷰를 작성 요청 DTO")
    public static class CreateDto {
        @Schema(name = "리뷰 제목", example = "너무 맛있어요")
        private String title;
        @Schema(name = "리뷰 점수", example = "4.5")
        private Float score;
        @NotNull
        @Schema(name = "MemberID")
        private Long memberId;
    }
}
