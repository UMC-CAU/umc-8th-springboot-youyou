package com.umc.bios.global.security.controller;

import com.umc.bios.domain.member.dto.request.MemberRequestDto;
import com.umc.bios.global.security.service.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberCommandService memberCommandService;

    @Operation(summary = "회원가입 실습을 위한 임시 로그인 API", description = """
            회원가입 로직 실습을 위하여 일시적으로 추가한 View Controller의 로그인 API입니다.
            """)
    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDto.JoinDto request,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            memberCommandService.joinMember(request);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDto.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
