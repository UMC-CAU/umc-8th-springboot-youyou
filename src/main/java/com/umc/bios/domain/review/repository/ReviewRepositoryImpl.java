package com.umc.bios.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.entity.QMember;
import com.umc.bios.domain.review.entity.Review;
import com.umc.bios.domain.store.entity.Store;
import com.umc.bios.domain.store.repository.StoreRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final StoreRepository storeRepository;

    @Override
    public Review insertReviewFromMemberId(Long memberId, Long storeId, Float score, String title) {

        QMember member = QMember.member;

        Member foundMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found."));

        if (foundMember != null) {
            Review review = new Review();
            review.setMember(foundMember);
            review.setStore(store);
            review.setScore(score);
            review.setTitle(title);

            entityManager.persist(review);
            return review;
        }
        return null;
    }
}
