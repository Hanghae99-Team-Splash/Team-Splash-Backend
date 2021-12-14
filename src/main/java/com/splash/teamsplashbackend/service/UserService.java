package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.user.UserRequestDto;

import com.splash.teamsplashbackend.jwt.JwtProperties;
import com.splash.teamsplashbackend.model.User;
import com.splash.teamsplashbackend.repository.UserRepository;
import com.splash.teamsplashbackend.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;
import com.splash.teamsplashbackend.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    //회원가입처리
    @Transactional
    public String joinProcess(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        String name = userRequestDto.getName();
        String nickname = userRequestDto.getNickname();
        //회원가입 빈 값 금지
        UserValidator.checkNull(username, password, name, nickname);
        Optional<User>foundEmail = userRepository.findByUsername(username);
        //이메일 중복검사
        UserValidator.checkEmail(foundEmail);
        password = passwordEncoder.encode(userRequestDto.getPassword());
        User user = new User(username, password, name, nickname);
        userRepository.save(user);
        return "Success Join";
    }
    //로그인 처리
    public void loginProcess(UserRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        response.addHeader(JwtProperties.HEADER_STRING, jwtTokenProvider.createToken(user.getUsername(), Long.toString(user.getId())));
    }


}