package com.umc.bios.domain.mission.dto;

import com.umc.bios.domain.mission.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MissionResponseDto {

    private Integer reward;
    private LocalDate deadLine;
    private String missionSpec;

    public static MissionResponseDto toDto(Mission mission) {
        return MissionResponseDto.builder()
                .reward(mission.getReward())
                .deadLine(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChallengeDto {
        private Integer reward;
        private LocalDate deadLine;
        private String missionSpec;

        public static ChallengeDto toChallengeDto(Mission mission) {
            return MissionResponseDto.ChallengeDto.builder()
                    .reward(mission.getReward())
                    .deadLine(mission.getDeadline())
                    .missionSpec(mission.getMissionSpec())
                    .build();
        }
    }
}
