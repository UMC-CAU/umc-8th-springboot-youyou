package com.umc.bios.domain.member.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.member.common.Gender;
import com.umc.bios.domain.member.common.MemberStatus;
import com.umc.bios.domain.member.common.Role;
import com.umc.bios.domain.member.common.SocialType;
import com.umc.bios.domain.mission.entity.MemberMission;
import com.umc.bios.domain.mission.entity.MemberPrefer;
import com.umc.bios.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="member_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate inactiveDate;

    @Column(nullable = true, length = 50)
    private String email;

    @Setter
    @Column(name = "password")
    private String password;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
