package com.umc.bios.domain.mission.repository;

import com.umc.bios.domain.mission.common.MissionStatus;
import com.umc.bios.domain.mission.entity.MemberMission;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findByStatus(MissionStatus missionStatus);
}
