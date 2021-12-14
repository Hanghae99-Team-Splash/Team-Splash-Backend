package com.splash.teamsplashbackend.model;

import com.splash.teamsplashbackend.dto.user.UserRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// JUnit을 이용한 단위 테스트
class UserTest {
    @Test
    @DisplayName("정상 케이스")
    void createUUser_Normal() {
        // given
        String username = "nao@naver.com";
        String password = "qwer";
        String name = "Lim";
        String nickname = "nao";

        UserRequestDto userRequestDto = new UserRequestDto(
                  username
                , password
                , name
                , nickname
                );

        // when
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .nickname(userRequestDto.getNickname())
                .build();

        // then

        assertNull(user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(name, user.getName());
        assertEquals(nickname, user.getNickname());
    }
}