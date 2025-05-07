package com.umc.bios.domain.store.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.mission.entity.Mission;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "store")
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Float score;

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList;
}
