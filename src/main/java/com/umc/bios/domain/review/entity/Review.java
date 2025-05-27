package com.umc.bios.domain.review.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.review.dto.response.ReviewResponseDto;
import com.umc.bios.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "score")
    private Float score;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public static ReviewResponseDto toResponseDto(Review review) {
        return ReviewResponseDto.builder()
                .title(review.getTitle())
                .score(review.getScore())
                .build();
    }
}
