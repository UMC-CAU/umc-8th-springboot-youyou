package com.umc.bios.domain.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello() {
        return "Hello World";
    }

    public void sayGoodbye(HttpServletRequest request, HttpServletResponse response) {

    }
}
