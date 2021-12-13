package com.splash.teamsplashbackend.dto.tag;


import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TagResponseDto {
    private String tagname;

    private List<PhotoBoardResponseDto> photoboards;

    @Builder
    public TagResponseDto(String tagname, List<PhotoBoardResponseDto> photoboards) {
        this.tagname = tagname;
        this.photoboards = photoboards;
    }

}
