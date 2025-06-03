package com.umc.bios.domain.store.controller;

import com.umc.bios.domain.store.dto.StoreRequestDto;
import com.umc.bios.domain.store.service.StoreQueryService;
import com.umc.bios.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "Store API Controller")
public class StoreController {

    private final StoreQueryService storeService;

    @Operation(summary = "가게 추가 API", description = """
            특정 지역에 가게를 추가합니다.<br>
            """)
    @PostMapping("")
    public ResponseEntity<CommonResponse<?>> create(
            @RequestBody @Valid StoreRequestDto.CreateDto requestDto
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.createStore(requestDto))
        );
    }

    @Operation(summary = "특정 지역의 가게 전체 조회 API", description = """
            특정 지역의 가게를 전체 조회합니다.<br>
            """)
    @GetMapping("/regions")
    public ResponseEntity<CommonResponse<?>> getAll(
            @RequestParam String regionName) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.readAllStores(regionName))
        );
    }

    @Operation(summary = "특정 가게의 미션 전체 조회 API")
    @GetMapping("/{storeId}/missions")
    public ResponseEntity<CommonResponse<?>> readAllMissions(
            @PathVariable Long storeId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.readAllMissions(storeId, page, size))
        );
    }

    @Operation(summary = "특정 가게의 리뷰 전체 조회 API", description = """
            특정 가게에 달린 리뷰 전체를 조회하는 API입니다.
            """)
    @GetMapping("/{storedId}/review")
    public ResponseEntity<CommonResponse<?>> readAllReviews(
            @PathVariable Long storedId
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.readAllReviews(storedId))
        );
    }

    @Operation(summary = "가게 상세 조회 API")
    @GetMapping("/{storeId}")
    public ResponseEntity<CommonResponse<?>> findStore(
            @PathVariable Long storeId
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.find(storeId))
        );
    }

    @Operation(summary = "가게 전체 조회 API")
    @GetMapping("")
    public ResponseEntity<CommonResponse<?>> findAll() {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.findAll())
        );
    }

}
