package com.umc.bios.domain.member.repository;

import com.umc.bios.domain.member.entity.MemberAgree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAgreeRepository extends JpaRepository<MemberAgree, Long> {

    @Modifying
    @Query("DELETE FROM MemberAgree ma WHERE ma.member.id = :memberId")
    void deleteAllByMemberId(@Param("memberId") Long memberId);
}
