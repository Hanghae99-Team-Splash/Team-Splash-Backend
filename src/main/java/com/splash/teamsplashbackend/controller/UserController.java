package com.splash.teamsplashbackend.controller;


import com.splash.teamsplashbackend.dto.user.SignupRequestDto;
import com.splash.teamsplashbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/user/join")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.joinProcess(signupRequestDto);
    }



}
