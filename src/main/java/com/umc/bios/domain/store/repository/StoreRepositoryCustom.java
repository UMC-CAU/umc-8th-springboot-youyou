package com.umc.bios.domain.store.repository;

import com.umc.bios.domain.mission.entity.Mission;
import com.umc.bios.domain.store.entity.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
    List<Store> findByRegionName(String regionName);
}
