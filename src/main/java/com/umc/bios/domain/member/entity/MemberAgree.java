package com.umc.bios.domain.member.entity;

import com.umc.bios.domain.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "member_agree")
public class MemberAgree extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id")
    private Terms terms;
}
