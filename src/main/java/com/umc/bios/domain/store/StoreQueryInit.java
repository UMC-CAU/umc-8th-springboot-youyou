package com.umc.bios.domain.store;

import com.umc.bios.domain.store.service.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreQueryInit implements CommandLineRunner {

    private final StoreQueryService storeQueryService;

    @Override
    public void run(String[] args) {

        // 파라미터 값 설정
        String name = "요아정";
        Float score = 4.0f;

        // 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
        System.out.println("Executing findStoresByNameAndScore with parameters:");
        System.out.println("Name: " + name);
        System.out.println("Score: " + score);

        storeQueryService.findStoresByNameAndScore(name, score)
                .forEach(System.out::println);
    }
}
