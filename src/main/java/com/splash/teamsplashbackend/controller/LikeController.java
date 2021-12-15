package com.splash.teamsplashbackend.controller;


import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.service.LikeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @ApiOperation("게시물 좋아요 기능")
    @PostMapping("/api/board/like/{boardId}")
    public String registLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId) {
        if(userDetails == null) {
            throw new IllegalArgumentException("회원가입 후 이용해주세요");
        }
        return likeService.clickLike(userDetails, boardId);
    }

}
