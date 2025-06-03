package com.umc.bios.domain.member.repository;

import com.umc.bios.domain.member.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
