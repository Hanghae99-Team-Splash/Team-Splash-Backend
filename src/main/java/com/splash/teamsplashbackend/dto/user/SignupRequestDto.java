package com.splash.teamsplashbackend.dto.user;


import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class SignupRequestDto {
    @Email
    private String username;

    private String password;

    private String name;

    private String nickname;
}
