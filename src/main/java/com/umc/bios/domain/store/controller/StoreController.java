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
            path에 추가될 지역의 아이디를 입력해주세요.
            """)
    @PostMapping("/{regionId}")
    public ResponseEntity<CommonResponse<?>> create(
            @PathVariable Long regionId,
            @RequestBody @Valid StoreRequestDto.CreateDto requestDto
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(storeService.createStore(regionId, requestDto))
        );
    }
}
