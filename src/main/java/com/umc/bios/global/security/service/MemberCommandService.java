package com.umc.bios.global.security.service;

import com.umc.bios.domain.member.dto.request.MemberRequestDto;
import com.umc.bios.domain.member.dto.response.MemberResponseDto;
import com.umc.bios.domain.member.entity.FoodCategory;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.entity.QFoodCategory;
import com.umc.bios.domain.member.repository.FoodCategoryRepository;
import com.umc.bios.domain.member.repository.MemberRepository;
import com.umc.bios.domain.mission.entity.MemberPrefer;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import jdk.jfr.TransitionTo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto.JoinResultDTO joinMember(MemberRequestDto.JoinDto request) {


        Member newMember = MemberRequestDto.toMember(request);
        newMember.setPassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> CustomException.of(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPrefer.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return MemberResponseDto.toJoinResultDto(memberRepository.save(newMember));
    }
}
