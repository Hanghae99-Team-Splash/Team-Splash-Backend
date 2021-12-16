package com.splash.teamsplashbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TeamSplashBackendApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:s3.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(TeamSplashBackendApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
