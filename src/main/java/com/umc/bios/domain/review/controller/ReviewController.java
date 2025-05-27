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

    @Operation(summary = "작성 리뷰 전체 조회 API", description = """
            특정 회원이 작성한 리뷰 전체에 대하여 조회합니다.
            """)
    @GetMapping()
    public ResponseEntity<CommonResponse<?>> readAll(
            // TODO: Security Context로 memberId 얻는 로직으롭 변경
            @RequestParam Long memberId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(reviewService.readAll(memberId, page, size))
        );
    }

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

    @Operation(summary = "특정 리뷰 조회 API", description = """
            특정 리뷰 상세 조회를 위한 API입니다.<br>
            reviewId에 조회하고 싶은 리뷰의 ID 값을 넣어주세요.
            """)
    @GetMapping("/{reviewId}")
    public ResponseEntity<CommonResponse<?>> get(
            @PathVariable Long reviewId
    ) {
        return null;
    }
}
