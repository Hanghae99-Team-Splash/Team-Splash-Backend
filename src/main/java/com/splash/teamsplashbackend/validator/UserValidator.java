package com.splash.teamsplashbackend.validator;



import com.splash.teamsplashbackend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserValidator {
    //이메일 중복검사
    public void checkEmail(Optional<User> foundEmail) {
        if(foundEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
    }
    public void checkIsEmpty(String username, String password, String name, String nickname) {
        if(username.isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수 입력 값입니다");
        }
        if(password.isEmpty()) {
            throw new IllegalArgumentException("패스워드는 필수 입력 값입니다");
        }
        if(name.isEmpty()) {
            throw new IllegalArgumentException("이름은 필수 입력 값입니다");
        }
        if(nickname.isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력 값입니다");
        }
    }

}
