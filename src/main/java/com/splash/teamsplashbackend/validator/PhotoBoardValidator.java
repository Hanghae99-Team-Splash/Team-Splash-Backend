package com.splash.teamsplashbackend.validator;

import com.splash.teamsplashbackend.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
public class PhotoBoardValidator {

    public static void checkMultipartFileNullAndSize(MultipartFile multipartFile) {
        if(multipartFile == null || multipartFile.getSize() == 0) {
            throw new NullPointerException("등록하려는 게시글에 이미지가 없습니다.");
        }
    }
    //이메일 중복검사
    public static void photoBoardCheckIsEmpty(String location, String description, String tagname) {
        if (location.isEmpty()) {
            throw new IllegalArgumentException("location은 필수 입력 값입니다");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("description은 필수 입력 값입니다");
        }
        if (tagname.isEmpty()) {
            throw new IllegalArgumentException("tagname은 필수 입력 값입니다");
        }
    }
}
