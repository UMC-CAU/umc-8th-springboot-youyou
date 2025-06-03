package com.umc.bios.domain.review.repository;

import com.umc.bios.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    Page<Review> findAllByMemberId(Pageable pageable, Long memberId);
}
