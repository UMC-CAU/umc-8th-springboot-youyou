package com.umc.bios.domain.mission.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.mission.common.MissionStatus;
import com.umc.bios.domain.mission.dto.MissionResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Entity
@Getter
@RequiredArgsConstructor
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

    public MemberMission(MissionStatus missionStatus, Member member, Mission mission) {
        this.status = missionStatus;
        this.member = member;
        this.mission = mission;
    }

    public static MissionResponseDto toResponseDto(MemberMission memberMission) {
        return MissionResponseDto.builder()
                .reward(memberMission.getMission().getReward())
                .deadLine(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .build();
    }
}