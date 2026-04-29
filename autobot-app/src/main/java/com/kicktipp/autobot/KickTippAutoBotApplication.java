package com.kicktipp.autobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.kicktipp.autobot")
public class KickTippAutoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickTippAutoBotApplication.class, args);
    }
}
