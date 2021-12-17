package com.splash.teamsplashbackend.validator;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PhotoBoardValidator {

    public static void checkMultipartFileNullAndSize(MultipartFile multipartFile) {
        if(multipartFile == null || multipartFile.getSize() == 0) {
            throw new NullPointerException("등록하려는 게시글에 이미지가 없습니다.");
        }
    }

    public static void photoBoardRequestEmptyCheck(PhotoBoardRequestDto photoBoardRequestDto) {
        if (photoBoardRequestDto.getLocation().isEmpty()) {
            throw new IllegalArgumentException("location은 필수 입력 값입니다");
        }
        if (photoBoardRequestDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("description은 필수 입력 값입니다");
        }
        if (photoBoardRequestDto.getTagname().isEmpty()) {
            throw new IllegalArgumentException("tagname은 필수 입력 값입니다");
        }
    }
}
