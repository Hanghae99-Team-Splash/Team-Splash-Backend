package com.splash.teamsplashbackend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.splash.teamsplashbackend.dto.user.UserRequestDto;
import com.splash.teamsplashbackend.service.KakaoUserService;
import com.splash.teamsplashbackend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    //회원가입
    @ApiOperation(value = "회원가입")
    @PostMapping("/user/join")
    public String signup(@RequestBody UserRequestDto signupRequestDto) {
        return userService.joinProcess(signupRequestDto);
    }

    //카카오 회원가입 및 로그인 요청 처리
    @ApiOperation(value = "카카오톡 로그인")
    @GetMapping("/user/kakao/callback")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code, response);
    }

    //로그인 성공
    @ApiOperation(value = "로그인")
    @PostMapping("/user/login")
    public String login(@RequestBody UserRequestDto requestDto, HttpServletResponse response) {
        userService.loginProcess(requestDto, response);
        return "Suceess Login";
    }

}
