package com.splash.teamsplashbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamSplashBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamSplashBackendApplication.class, args);
    }

}
