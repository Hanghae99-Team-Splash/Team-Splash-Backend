package com.splash.teamsplashbackend.dto.likes;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LikesResponseDto {

    private List<Long> userIds;

    @Builder
    public LikesResponseDto(List<Long> userIds) {
        this.userIds = userIds;
    }
}
