package com.umc.bios.global.test;

import com.umc.bios.global.exception.CustomException;
import com.umc.bios.global.response.CommonResponse;
import com.umc.bios.global.response.ErrorStatus;
import com.umc.bios.global.response.SuccessStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "응답 테스트 API")
public class TestController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String helloWorld() {
        return helloService.sayHello();
    }

    @GetMapping("/no-data")
    public ResponseEntity<CommonResponse<?>> testOnSuccess() {
        return ResponseEntity.ok(CommonResponse.of(SuccessStatus._OK));
    }

    @GetMapping("/data")
    public ResponseEntity<CommonResponse<?>> testOnSuccessWithData() {
        return ResponseEntity.ok(
                CommonResponse.of(SuccessStatus._OK, "Good")
        );
    }

    @GetMapping("/failure")
    public ResponseEntity<?> testOnFailure() {
        return ResponseEntity.ok(
                CommonResponse.onFailure(ErrorStatus.MEMBER_NOT_FOUND, null)
        );
    }

    @GetMapping("/error")
    public void throwError() {
        throw CustomException.of(ErrorStatus._INTERNAL_SERVER_ERROR);
    }
}
