package com.umc.bios.domain.store.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class StoreRequestDto {

    @Getter
    @Builder
    public static class CreateDto {
        @NotNull
        String name;
        @NotNull
        Long regionId;

        String address;
    }
}
