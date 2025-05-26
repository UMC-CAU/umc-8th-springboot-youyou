package com.umc.bios.global.util;

import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//@Component
//@RequiredArgsConstructor
//@Slf4j
//@Profile("dev")
//public class WebhookNotifier {
//
//    @Value("${webhook.discord-url}")
//    private String discordWebhookUrl;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public void send(String message) {
//        if (!StringUtils.hasText(discordWebhookUrl)) return;
//
//        Map<String, String> payload = Map.of("content", message);
//
//        try {
//            restTemplate.postForEntity(discordWebhookUrl, payload, String.class);
//        } catch (Exception e) {
//            log.warn("웹훅 전송 실패: {} ", e.getMessage());
//            throw CustomException.of(ErrorStatus.FAILED_SEND_WEBHOOK);
//        }
//    }
//}
