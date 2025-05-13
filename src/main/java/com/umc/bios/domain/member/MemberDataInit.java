package com.umc.bios.domain.member;

import com.umc.bios.domain.member.common.Gender;
import com.umc.bios.domain.member.common.MemberStatus;
import com.umc.bios.domain.member.common.SocialType;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
@Slf4j
@RequiredArgsConstructor
public class MemberDataInit {

    private final MemberRepository memberRepository;

    @Bean
    public CommandLineRunner initMember() {
        return args -> {
            // 임시 Member 생성
            long memberCount = memberRepository.count();
            if(memberCount == 0) {
                IntStream.rangeClosed(1, 50).forEach(i -> {
                    Member member = Member.builder()
                            .name("test" + i)
                            .address("서울시")
                            .specAddress("동작구")
                            .gender(Gender.FEMALE)
                            .socialType(SocialType.KAKAO)
                            .status(MemberStatus.ACTIVE)
                            .email("test" + i + "@test.com")
                            .point(0)
                            .build();
                    memberRepository.save(member);
                });
                log.info("Saved new member");
            } else {
                log.info("Found {} member(s) in DB", memberCount);
            }
        };
    }
}
