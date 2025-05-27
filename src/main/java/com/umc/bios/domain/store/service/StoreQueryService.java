package com.umc.bios.domain.store.service;

import com.umc.bios.domain.mission.dto.MissionResponseDto;
import com.umc.bios.domain.review.repository.ReviewRepository;
import com.umc.bios.domain.store.dto.StoreRequestDto;
import com.umc.bios.domain.store.dto.StoreResponseDto;
import com.umc.bios.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StoreQueryService {

    List<Store> findStoresByNameAndScore(String name, Float score);

    StoreResponseDto createStore(StoreRequestDto.CreateDto storeRequestDto);

    Page<MissionResponseDto> readAllMissions(Long storeId, int page, int size);

    Page<ReviewRepository> readAllReviews(Long storeId);

    List<StoreResponseDto> readAllStores(String regionName);

    StoreResponseDto find(Long storeId);

    List<StoreResponseDto> findAll();
}
