package com.umc.bios.domain.review.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;
}
