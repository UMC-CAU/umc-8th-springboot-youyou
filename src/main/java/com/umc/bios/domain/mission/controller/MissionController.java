package com.umc.bios.domain.mission.controller;

import com.umc.bios.domain.mission.dto.MissionRequestDto;
import com.umc.bios.domain.mission.service.MissionService;
import com.umc.bios.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/missions")
@Tag(name = "Mission API Controller")
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "미션 등록 API 입니다.", description = """
            특정 가게에 미션을 등록하는 API입니다.
            """)
    @PostMapping("/{storeId}")
    public ResponseEntity<CommonResponse<?>> create(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequestDto.CreateDto requestDto) {

        return ResponseEntity.ok(
                CommonResponse.onSuccess(missionService.createToStore(storeId, requestDto))
        );
    }

    // 미션 도전하기
    @Operation(summary = "미션 도전 API입니다.")
    @PatchMapping
    public ResponseEntity<CommonResponse<?>> challenge(
            @RequestParam Long storeId,
            // TODO : SecurityContext를 통한 memberID 취득으로 변경
            @RequestParam Long memberId,
            @RequestParam Long missionId
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(missionService.challenge(storeId, memberId, missionId))
        );
    }

    // 진행중인 미션 조회
    @Operation(summary = "진행 중인 미션 조회 API입니다.")
    @GetMapping
    public ResponseEntity<CommonResponse<?>> getMissions(
            // TODO : SecurityContext를 통한 memberID 취득으로 변경
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(missionService.readAll(memberId))
        );
    }

    // 미션 완료로 변경
    @Operation(summary = "진행 중인 미션 조회 API입니다.")
    @PatchMapping("/complete")
    public ResponseEntity<CommonResponse<?>> doneMission(
            @RequestParam Long storeId,
            // TODO : SecurityContext를 통한 memberID 취득으로 변경
            @RequestParam Long memberId,
            @RequestParam Long missionId

    ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(missionService.done(storeId, memberId, missionId))
        );
    }
}
