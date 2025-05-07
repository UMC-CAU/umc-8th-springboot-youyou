package com.umc.bios.domain.member.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.mission.entity.MemberPrefer;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_category")
public class FoodCategory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "foodCategory")
    private List<MemberPrefer> memberPreferList = new ArrayList<>();
}
