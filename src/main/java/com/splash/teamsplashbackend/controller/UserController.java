package com.splash.teamsplashbackend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.dto.user.UserRequestDto;
import com.splash.teamsplashbackend.dto.user.UserResponseDto;
import com.splash.teamsplashbackend.service.KakaoUserService;
import com.splash.teamsplashbackend.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    //회원가입
    @ApiOperation(value = "회원가입")
    @PostMapping("/user/join")
    public String signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.joinProcess(userRequestDto);
    }

    //카카오 회원가입 및 로그인 요청 처리
    @ApiOperation(value = "카카오톡 로그인")
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        return kakaoUserService.kakaoLogin(code, response);
    }

    //로그인 성공
    @ApiOperation(value = "로그인")
    @PostMapping("/user/login")
    public UserResponseDto login(@RequestBody UserRequestDto requestDto, HttpServletResponse response) {
        return userService.loginProcess(requestDto, response);
    }

}