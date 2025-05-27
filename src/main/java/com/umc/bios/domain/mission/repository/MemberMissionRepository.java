package com.umc.bios.domain.mission.repository;

import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.mission.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    Optional<MemberMission> findByMember(Member member);
}
