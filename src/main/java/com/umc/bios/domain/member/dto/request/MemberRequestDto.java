package com.umc.bios.domain.member.dto.request;

import com.umc.bios.domain.member.common.Gender;
import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.validation.ExistCategories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MemberRequestDto {
    @Getter
    public static class JoinDto{
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }

    public static Member toMember(MemberRequestDto.JoinDto requestDto) {
        Gender gender = null;

        switch (requestDto.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(requestDto.getAddress())
                .specAddress(requestDto.getSpecAddress())
                .gender(gender)
                .name(requestDto.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
