package com.splash.teamsplashbackend.controller;

import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.service.PhotoBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoBoardController {
    private final PhotoBoardService photoBoardService;

    @ApiOperation(value = "사진 게시물 등록")
    @PostMapping("/api/board")
    public Long photoBoardUpload(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart @Valid PhotoBoardRequestDto photoBoardRequestDto,
            @RequestPart(required = false) @Valid MultipartFile multipartFile
    ) {
        return photoBoardService.uploadPhotoPost(photoBoardRequestDto, multipartFile, userDetails.getUser());
    }

    @ApiOperation(value = "사진 게시물 수정")
    @PutMapping("/api/board/edit/{id}")
    public void photoBoardEdit(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            @RequestBody PhotoBoardRequestDto photoBoardRequestDto
    ) {
        photoBoardService.editPhotoBoard(id,photoBoardRequestDto, userDetails.getUser());
    }

    @ApiOperation(value = "전체 게시글 조회")
    @GetMapping("/api/main")
    public List<PhotoBoardResponseDto> photoBoardGetList() {
        return photoBoardService.findAll();
    }

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/api/board/detail/{id}")
    public PhotoBoardResponseDto photoBoardGetDetail(
            @PathVariable Long id
    ) {
        return photoBoardService.findPhotoBoard(id);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/api/board/detail/{id}")
    public void photoBoardDelete(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id
    ) {
        photoBoardService.deletePhotoBoard(id, userDetails.getUser().getId());
    }

    //유저가 쓴 게시글 목록 가져오기
    @ApiOperation(value = "유저가 쓴 게시글 목록 가져오기")
    @GetMapping("/api/user/mypage/boardlist")
    public List<PhotoBoardResponseDto> getUserPhotoBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return photoBoardService.findUserBoardList(userDetails);
    }

    //내가 좋아요 누른 목록 가져오기
    @ApiOperation(value = "유저가 좋아요 누른 목록 가져오기")
    @GetMapping("/api/user/mypage/likelist")
    public List<PhotoBoardResponseDto> getUserLikePhotoBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return photoBoardService.findUserLikePhotoBoardList(userDetails);
    }
}
