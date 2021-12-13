package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.user.SignupRequestDto;

import com.splash.teamsplashbackend.model.User;
import com.splash.teamsplashbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //회원가입처리
    public String joinProcess(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String name = signupRequestDto.getName();
        String nickname = signupRequestDto.getNickname();
        Optional<User>foundEmail = userRepository.findByUsername(username);
        if(foundEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
        User user = new User(username, password, name, nickname);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}
