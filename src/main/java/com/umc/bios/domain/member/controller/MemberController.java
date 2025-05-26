package com.umc.bios.domain.member.controller;

import com.umc.bios.domain.member.dto.request.MemberRequestDto;
import com.umc.bios.domain.member.service.MemberService;
import com.umc.bios.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "Member API Controller")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입 API", description = """
            회원 가입을 위한 API 입니다.
            """)
    @PostMapping()
    public ResponseEntity<CommonResponse<?>> join(
            @RequestBody @Valid MemberRequestDto.JoinDto requestDto
            ) {
        return ResponseEntity.ok(
                CommonResponse.onSuccess(memberService.joinMember(requestDto))
        );
    }
}
