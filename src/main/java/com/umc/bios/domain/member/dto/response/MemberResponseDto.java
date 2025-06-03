package com.umc.bios.domain.member.dto.response;

import com.umc.bios.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

    public static MemberResponseDto.JoinResultDTO toJoinResultDto(Member member) {
        return JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt()).build();
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResultDTO {
        Long memberId;
        String accessToken;
    }
}
