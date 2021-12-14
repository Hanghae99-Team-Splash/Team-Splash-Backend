package com.splash.teamsplashbackend.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserResponseDto {

    private String nickname;

    private Long userId;

    public UserResponseDto(String nickname, Long userId) {
        this.nickname = nickname;
        this.userId = userId;
    }

}
