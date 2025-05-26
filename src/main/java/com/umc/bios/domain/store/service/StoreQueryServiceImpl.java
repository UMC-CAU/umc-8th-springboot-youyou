package com.umc.bios.domain.store.service;

import com.umc.bios.domain.store.dto.StoreRequestDto;
import com.umc.bios.domain.store.dto.StoreResponseDto;
import com.umc.bios.domain.store.entity.Region;
import com.umc.bios.domain.store.entity.Store;
import com.umc.bios.domain.store.repository.RegionRepository;
import com.umc.bios.domain.store.repository.StoreRepository;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Transactional
    @Override
    public StoreResponseDto createStore(Long regionId, StoreRequestDto.CreateDto requestDto) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .build();
        storeRepository.save(store);
        store.setRegion(region);

        return StoreResponseDto.toDto(store);
    }

}
