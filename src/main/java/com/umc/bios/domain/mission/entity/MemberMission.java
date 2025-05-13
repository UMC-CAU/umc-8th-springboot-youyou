package com.umc.bios.domain.mission.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.mission.common.MissionStatus;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="mission_id")
    private Mission mission;
}