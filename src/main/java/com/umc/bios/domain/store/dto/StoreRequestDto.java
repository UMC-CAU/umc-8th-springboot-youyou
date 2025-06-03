package com.umc.bios.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class StoreRequestDto {

    @Getter
    @Builder
    @Schema(description = "가게 등록을 위해 필요한 정보")
    public static class CreateDto {
        @Schema(name = "가게 이름", example = "요아정")
        @NotNull
        String name;
        @Schema(name = "지역 이름", example = "서울")
        @NotNull
        String regionName;
        @Schema(name = "상세 주소", example = "서울시 마포구 연남동")
        String address;
    }
}
