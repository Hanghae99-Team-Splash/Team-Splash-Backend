package com.splash.teamsplashbackend.dto.tag;


import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class TagResponseDto {
    private String tagname;

    private List<PhotoBoardResponseDto> photoboards;


}
