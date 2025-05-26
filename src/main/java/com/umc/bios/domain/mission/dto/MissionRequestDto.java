package com.umc.bios.domain.mission.dto;

import com.umc.bios.domain.mission.common.MissionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    @Builder
    public static class CreateDto {
        @NotNull
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
    }

    @Getter
    @Builder
    public static class ChallengeDto {
        private MissionStatus status;
    }
}
