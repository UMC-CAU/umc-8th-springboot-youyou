package com.umc.bios.domain.store.dto;

import com.umc.bios.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreResponseDto {

    private String name;
    private String address;
    private Float score;
}
