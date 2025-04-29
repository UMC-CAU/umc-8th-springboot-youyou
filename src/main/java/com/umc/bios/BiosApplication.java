package com.umc.bios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BiosApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiosApplication.class, args);
    }

}
