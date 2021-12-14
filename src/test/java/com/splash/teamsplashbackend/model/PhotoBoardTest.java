package com.splash.teamsplashbackend.model;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

// JUnit을 이용한 단위 테스트
class PhotoBoardTest {
    @Test
    @DisplayName("정상 클래스")
    void createPhotoBoard_Normal() {
        // given
        String nickname = "닉네임";
        String location = "로케이션";
        String tagname = "태그네임";
        String description = "디스크립션";
        String img = "이미지URL";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime modifiedAt = LocalDateTime.now();

        // when
        PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                nickname,
                location,
                tagname,
                description
        );

        PhotoBoard photoBoard = PhotoBoard.builder()
                .location(photoBoardRequestDto.getLocation())
                .tagname(photoBoardRequestDto.getTagname())
                .description(photoBoardRequestDto.getDescription())
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .img(img)
                .build();


        // then
        assertNull(photoBoard.getId());
        assertEquals(img, photoBoard.getImg());
        assertEquals(location, photoBoard.getLocation());
        assertEquals(description, photoBoard.getDescription());
        assertEquals(0, photoBoard.getViews());
        assertEquals(tagname, photoBoard.getTagname());
        assertEquals(createdAt, photoBoard.getCreatedAt());
        assertEquals(modifiedAt, photoBoard.getModifiedAt());

    }
}