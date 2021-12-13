package com.splash.teamsplashbackend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.splash.teamsplashbackend.dto.user.SignupRequestDto;
import com.splash.teamsplashbackend.service.KakaoUserService;
import com.splash.teamsplashbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    //회원가입
    @PostMapping("/user/join")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.joinProcess(signupRequestDto);
    }

    //카카오 회원가입 및 로그인 요청 처리
    @GetMapping("/user/kakao/callback")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code, response);
    }


}
