package com.splash.teamsplashbackend.dto.photoBoard;

import lombok.Getter;

@Getter
public class PhotoBoardRequestDto {
    private String username;
    private Long userid;
    private String location;
    private String tagname;
    private String description;
}
