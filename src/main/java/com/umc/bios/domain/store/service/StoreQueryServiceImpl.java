package com.umc.bios.domain.store.service;

import com.umc.bios.domain.mission.dto.MissionResponseDto;
import com.umc.bios.domain.mission.entity.Mission;
import com.umc.bios.domain.review.repository.ReviewRepository;
import com.umc.bios.domain.store.dto.StoreRequestDto;
import com.umc.bios.domain.store.dto.StoreResponseDto;
import com.umc.bios.domain.store.entity.Region;
import com.umc.bios.domain.store.entity.Store;
import com.umc.bios.domain.store.repository.RegionRepository;
import com.umc.bios.domain.store.repository.StoreRepository;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Transactional
    @Override
    public StoreResponseDto createStore(StoreRequestDto.CreateDto requestDto) {
        Region region = regionRepository.findByName(requestDto.getRegionName())
                .orElseThrow(() -> CustomException.of(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .build();
        storeRepository.save(store);
        store.setRegion(region);

        return Store.toResponseDto(store);
    }

    @Transactional(readOnly = true)
    public Page<MissionResponseDto> readAllMissions(Long storeId, int page, int size) {

        Store foundStore = storeRepository.findById(storeId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.STORE_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, size);

        return storeRepository.findMissionsByStore(foundStore, pageable)
                .map(Mission::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<ReviewRepository> readAllReviews(Long storeId) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDto> readAllStores(String regionName) {

        return storeRepository.findByRegionName(regionName)
                .stream()
                .map(Store::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponseDto find(Long storeId) {
        return storeRepository.findById(storeId)
                .map(Store::toResponseDto)
                .orElseThrow(() -> CustomException.of(ErrorStatus.STORE_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDto> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(Store::toResponseDto)
                .toList();
    }
}
