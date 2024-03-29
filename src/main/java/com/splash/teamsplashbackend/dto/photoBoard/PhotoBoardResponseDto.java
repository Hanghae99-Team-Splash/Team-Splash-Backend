package com.splash.teamsplashbackend.dto.photoBoard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhotoBoardResponseDto {
    private Long boardId;
    private Long userId;
    private String img;
    private String nickname;
    private String location;
    private String tagname;
    private String description;
    private String size;
    private String modifiedAt;
    private int views;
    private int likeCnt;



}
