package com.umc.bios.domain.mission.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.mission.dto.MissionResponseDto;
import com.umc.bios.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="mission_id")
    private Long id;

    @Column(name = "reward")
    private Integer reward;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "mission_spec")
    private String missionSpec;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public static MissionResponseDto toResponseDto(Mission mission) {
        return MissionResponseDto.builder()
                .reward(mission.getReward())
                .deadLine(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }
}
