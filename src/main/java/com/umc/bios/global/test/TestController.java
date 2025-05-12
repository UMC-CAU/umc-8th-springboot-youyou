package com.umc.bios.global.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String helloWorld() {
        return helloService.sayHello();
    }


}
