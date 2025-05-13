package com.umc.bios.domain.mission.repository;

import com.umc.bios.domain.mission.common.MissionStatus;
import com.umc.bios.domain.mission.entity.MemberMission;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findByStatus(MissionStatus missionStatus);
}
