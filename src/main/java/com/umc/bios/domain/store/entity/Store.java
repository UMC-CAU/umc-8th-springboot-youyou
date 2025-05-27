package com.umc.bios.domain.store.entity;

import com.umc.bios.domain.common.BaseEntity;
import com.umc.bios.domain.mission.entity.Mission;
import com.umc.bios.domain.store.dto.StoreResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "store")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") + // region의 이름 출력
                '}';
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public static StoreResponseDto toResponseDto(Store store) {
        return StoreResponseDto.builder()
                .name(store.getName())
                .address(store.getAddress())
                .score(store.getScore())
                .build();
    }
}
