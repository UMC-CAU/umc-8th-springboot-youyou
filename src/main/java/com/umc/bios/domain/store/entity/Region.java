package com.umc.bios.domain.store.entity;

import com.umc.bios.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "region")
@Getter
public class Region extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column(name = "name")
    private String name;
}
