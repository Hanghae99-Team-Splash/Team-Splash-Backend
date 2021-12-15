package com.splash.teamsplashbackend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.dto.user.UserRequestDto;
import com.splash.teamsplashbackend.dto.user.UserResponseDto;
import com.splash.teamsplashbackend.service.KakaoUserService;
import com.splash.teamsplashbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = {"회원가입/카카오로그인/로그인"})
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;


    //회원가입
    @ApiOperation(value = "회원가입",notes ="4가지 정보를 통해 회원가입을 한다")
    @ApiImplicitParam(
            name = "userRequestDto"
            , value = "4가지 유저정보를 입력해주시면 됩니다."
            , required = true
            , defaultValue = ""
    )
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
    @ApiOperation(value = "로그인",notes ="이메일과 비밀번호를 통해 로그인을 할 수 있다")
    @ApiImplicitParam(
            name = "userRequestDto"
            , value = "username과 password 두가지 값만 입력해보시면 됩니다 그 외에는 안넣으셔도 됩니다."
            , required = true
            , defaultValue = ""
    )
    @PostMapping("/user/login")
    public UserResponseDto login(@RequestBody UserRequestDto requestDto, HttpServletResponse response) {
        return userService.loginProcess(requestDto, response);
    }

}