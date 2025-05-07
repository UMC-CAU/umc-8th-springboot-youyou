package com.umc.bios.domain.mission.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.bios.domain.mission.common.MissionStatus;
import com.umc.bios.domain.mission.entity.MemberMission;
import com.umc.bios.domain.mission.entity.QMemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<MemberMission> findByStatus(MissionStatus missionStatus) {

        return jpaQueryFactory
                .selectFrom(memberMission)
                .where(memberMission.status.eq(missionStatus))
                .offset(0)
                .limit(10)
                .fetch();
    }
}
