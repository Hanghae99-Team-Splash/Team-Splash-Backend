package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.user.SignupRequestDto;
import com.splash.teamsplashbackend.model.User;
import com.splash.teamsplashbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    //회원가입처리
    public String joinProcess(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        String name = signupRequestDto.getName();
        String username = signupRequestDto.getUsername();
        Optional<User> foundEmail = userRepository.findByEmail(email);
        if(foundEmail.isPresent()) {
            return "중복된 이메일이 존재합니다";
        }
        User user = new User(email, password, name, username);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

}
