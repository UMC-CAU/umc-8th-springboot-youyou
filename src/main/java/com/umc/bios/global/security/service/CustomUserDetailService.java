package com.umc.bios.global.security.service;

import com.umc.bios.domain.member.entity.Member;
import com.umc.bios.domain.member.repository.MemberRepository;
import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetails {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) throws CustomException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> CustomException.of(ErrorStatus.MEMBER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User
                .withUsername(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
