package com.splash.teamsplashbackend.dto.user;


import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String name;

    private String email;

    private String username;

    private String password;

}
