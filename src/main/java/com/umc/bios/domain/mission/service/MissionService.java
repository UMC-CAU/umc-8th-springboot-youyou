package com.umc.bios.domain.mission.service;

import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.repository.MemberRepository;
import com.umc.bios.domain.mission.common.MissionStatus;
import com.umc.bios.domain.mission.dto.MissionRequestDto;
import com.umc.bios.domain.mission.dto.MissionResponseDto;
import com.umc.bios.domain.mission.entity.MemberMission;
import com.umc.bios.domain.mission.entity.Mission;
import com.umc.bios.domain.mission.repository.MemberMissionRepository;
import com.umc.bios.domain.mission.repository.MissionRepository;
import com.umc.bios.domain.store.entity.Store;
import com.umc.bios.domain.store.repository.StoreRepository;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public MissionResponseDto createToStore(Long storeId, MissionRequestDto.CreateDto requestDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = Mission.builder()
                .reward(requestDto.getReward())
                .deadline(requestDto.getDeadline())
                .missionSpec(requestDto.getMissionSpec())
                .build();
        missionRepository.save(mission);
        mission.setStore(store);

        return MissionResponseDto.toDto(mission);
    }

    // 특정 가게의 미션을 도전 중인 미션에 추가
    @Transactional
    public MissionResponseDto.ChallengeDto challenge(
            Long storeId, Long memberId, Long missionId) {
        // TODO : SecurityContext를 통한 memberID 취득으로 변경

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.MISSION_NOT_FOUND));

        if (!store.getMissionList().contains(mission)) {
            throw CustomException.of(ErrorStatus.UNMATCHED_MISSION_TO_STORE);
        }


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> CustomException.of(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission memberMission = new MemberMission(MissionStatus.CHALLENGING, member, mission);
        memberMissionRepository.save(memberMission);
        return MissionResponseDto.ChallengeDto.toChallengeDto(memberMission.getMission());
    }

    @Transactional(readOnly = true)
    public List<MissionResponseDto> readAll(Long memberId) {
        return memberMissionRepository.findByStatus(MissionStatus.CHALLENGING)
                .stream()
                .map(MemberMission::toResponseDto)
                .toList();
    }
}
