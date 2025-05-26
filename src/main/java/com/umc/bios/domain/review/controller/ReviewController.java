package com.umc.bios.domain.review.controller;

import com.umc.bios.domain.review.dto.request.ReviewRequestDto;
import com.umc.bios.domain.review.service.ReviewService;
import com.umc.bios.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Review API Controller")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "특정 가게에 리뷰 추가 API", description = """
            특정 가게에 리뷰를 추가하는 API입니다.
            """)
    @PostMapping("/{storeId}")
    public ResponseEntity<CommonResponse<?>> create(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDto.CreateDto requestDto
            ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(
                        reviewService.createReview(storeId, requestDto)
                )
        );
    }
}
