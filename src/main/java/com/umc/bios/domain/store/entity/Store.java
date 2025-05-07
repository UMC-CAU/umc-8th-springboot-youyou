package com.umc.bios.domain.store.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.mission.entity.Mission;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "store")
public class Store extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "score")
    private Float score;

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
