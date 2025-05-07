package com.umc.bios.domain.member.entity;

import com.umc.bios.domain.common.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "terms")
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private Boolean optional;

    @OneToMany(mappedBy = "terms")
    private List<MemberAgree> memberAgreeList = new ArrayList<>();
}
