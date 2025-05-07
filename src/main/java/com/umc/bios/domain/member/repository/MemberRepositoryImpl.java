package com.umc.bios.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.bios.domain.member.entity.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;
}
