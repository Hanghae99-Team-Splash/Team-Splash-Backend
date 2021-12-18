package com.splash.teamsplashbackend.dto.photoBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoBoardRequestDto {
    private String nickname;
    private String location;
    private String tagname;
    private String description;
    private String size;
}
