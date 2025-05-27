package com.umc.bios.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewResponseDto {
    private String title;
    private String content;
    private Float score;
    private String writer;
}
