package com.umc.bios.domain.store.repository;

import com.umc.bios.domain.mission.entity.Mission;
import com.umc.bios.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

    @Query("SELECT m FROM Mission m WHERE m.store = :store")
    Page<Mission> findMissionsByStore(@Param("store") Store store, Pageable pageable);
}
