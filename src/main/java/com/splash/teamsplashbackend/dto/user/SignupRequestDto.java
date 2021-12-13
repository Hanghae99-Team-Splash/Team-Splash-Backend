package com.splash.teamsplashbackend.dto.user;


import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;

    private String password;

    private String name;

    private String nickname;
}
