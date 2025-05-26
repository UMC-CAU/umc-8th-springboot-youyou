package com.umc.bios.domain.store.service;

import com.umc.bios.domain.store.dto.StoreRequestDto;
import com.umc.bios.domain.store.dto.StoreResponseDto;
import com.umc.bios.domain.store.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);

    StoreResponseDto createStore(Long regionId, StoreRequestDto.CreateDto storeRequestDto);
}
