package com.umc.bios.domain.mission.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.entity.FoodCategory;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.review.entity.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "member_prefer")
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;
}