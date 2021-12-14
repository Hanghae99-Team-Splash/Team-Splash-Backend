package com.splash.teamsplashbackend.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @Email
    private String username;

    private String password;

    private String name;

    private String nickname;
}
