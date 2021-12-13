package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.user.SignupRequestDto;

import com.splash.teamsplashbackend.model.User;
import com.splash.teamsplashbackend.repository.UserRepository;
import com.splash.teamsplashbackend.validator.UserValidator;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //회원가입처리
    public String joinProcess(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String name = signupRequestDto.getName();
        String nickname = signupRequestDto.getNickname();
        //회원가입 빈 값 금지
        UserValidator.checkNull(username, password, name, nickname);
        Optional<User>foundEmail = userRepository.findByUsername(username);
        //이메일 중복검사
        UserValidator.checkEmail(foundEmail);
        password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(username, password, name, nickname);
        userRepository.save(user);
        return "Success Join";
    }



}
