package com.splash.teamsplashbackend.service;

import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.model.Likes;
import com.splash.teamsplashbackend.model.PhotoBoard;
import com.splash.teamsplashbackend.model.Tag;
import com.splash.teamsplashbackend.model.User;
import com.splash.teamsplashbackend.repository.LikeRepository;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import com.splash.teamsplashbackend.repository.TagRepository;
import com.splash.teamsplashbackend.utils.TimeCalculator;
import com.splash.teamsplashbackend.validator.PhotoBoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoBoardService {
    private final PhotoBoardRepository photoBoardRepository;
    private final LikeRepository likeRepository;
    private final TagRepository tagRepository;
//    private final S3Uploader s3Uploader;

//    private final String imageDirName = "static";

    //region 게시글 업로드
    public PhotoBoardResponseDto uploadPhotoPost(
            PhotoBoardRequestDto photoBoardRequestDto,
            MultipartFile multipartFile,
            User user
    ) {
        PhotoBoardValidator.checkMultipartFileNullAndSize(multipartFile);
        PhotoBoardValidator.photoBoardRequestEmptyCheck(photoBoardRequestDto);

//        String imageUrl = s3Uploader.upload(multipartFile, imageDirName);

        String tagname = photoBoardRequestDto.getTagname();
        Tag tag = tagRepository.findByTagname(tagname);
        if(tag == null) {
            tagRepository.save(Tag.builder()
                    .tagname(tagname)
                    .build());
        }
        PhotoBoard post = PhotoBoard.builder()
                        .img("https://images.unsplash.com/photo-1639353434411-088270055340?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")
                        .location(photoBoardRequestDto.getLocation())
                        .description(photoBoardRequestDto.getDescription())
                        .tagname(tagname)
                        .user(user)
                        .size(photoBoardRequestDto.getSize())
                        .build();
        photoBoardRepository.save(post);


        return PhotoBoardResponseDto.builder()
                .boardId(post.getId())
                .nickname(user.getNickname())
                .img(post.getImg())
                .location(post.getLocation())
                .description(post.getDescription())
                .tagname(post.getTagname())
                .size(post.getSize())
                .build();
    }
    //endregion

    //region 게시글 수정
    @Transactional
    public PhotoBoardResponseDto editPhotoBoard(
            Long boardId,
            PhotoBoardRequestDto photoBoardRequestDto,
            User user
    ) {
        PhotoBoardValidator.photoBoardRequestEmptyCheck(photoBoardRequestDto);
        PhotoBoard modifiedBoard = findPhotoBoardById(boardId);

        if (!modifiedBoard.getUser().getId().equals(user.getId()))
            throw new IllegalArgumentException("작성자가 아니라 게시글을 수정 할 수 없습니다.");

        modifiedBoard.update(photoBoardRequestDto);

        photoBoardRepository.save(modifiedBoard);

        return PhotoBoardResponseDto.builder()
                .boardId(modifiedBoard.getId())
                .nickname(user.getNickname())
                .img("https://images.unsplash.com/photo-1639353434411-088270055340?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80")
                .location(modifiedBoard.getLocation())
                .description(modifiedBoard.getDescription())
                .tagname(modifiedBoard.getTagname())
                .size(modifiedBoard.getSize())
                .build();
    }
    //endregion

    //region 게시글 전체 조회
    @Transactional
    public List<PhotoBoardResponseDto> findAll() {

        if(photoBoardRepository.count() == 0) {
            throw new NullPointerException("조회할 게시글이 없습니다.");
        }

        List<PhotoBoard> board = photoBoardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<PhotoBoardResponseDto> photoBoardResponseDtoList = new ArrayList<>();
        for(PhotoBoard photoBoard : board) {
            PhotoBoardResponseDto responseDto = PhotoBoardResponseDto.builder()
                    .boardId(photoBoard.getId())
                    .userId(photoBoard.getUser().getId())
                    .img(photoBoard.getImg())
                    .location(photoBoard.getLocation())
                    .tagname(photoBoard.getTagname())
                    .nickname(photoBoard.getUser().getNickname())
                    .description(photoBoard.getDescription())
                    .size(photoBoard.getSize())
                    .modifiedAt( TimeCalculator.timecalculator(photoBoard.getModifiedAt()))
                    .views(photoBoard.getViews())
                    .likeCnt(photoBoard.getLikeCnt())
                    .build();
            photoBoardResponseDtoList.add(responseDto);
        }

        return photoBoardResponseDtoList;
    }
    //endregion

    //region 게시글 하나 조회
    @Transactional
    public PhotoBoardResponseDto findPhotoBoard(
            Long boardId
    ) {
        PhotoBoard photoBoard = findPhotoBoardById(boardId);
//        photoBoard.setViews(photoBoard.getViews() + 1);
        photoBoardRepository.updateView(boardId);

        return PhotoBoardResponseDto.builder()
                .boardId(photoBoard.getId())
                .userId(photoBoard.getUser().getId())
                .img(photoBoard.getImg())
                .nickname(photoBoard.getUser().getNickname())
                .location(photoBoard.getLocation())
                .tagname(photoBoard.getTagname())
                .description(photoBoard.getDescription())
                .size(photoBoard.getSize())
                .modifiedAt(TimeCalculator.timecalculator(photoBoard.getModifiedAt()))
                .views(photoBoard.getViews())
                .likeCnt(photoBoard.getLikeCnt())
                .build();
    }
    //endregion

    //region 게시글 삭제
    public void deletePhotoBoard(
            Long boardId,
            Long loginUserId
    ) {
        PhotoBoard board = findPhotoBoardById(boardId);

        if (!board.getUser().getId().equals(loginUserId)) {
            throw new IllegalArgumentException("작성자가 아니라 게시글을 삭제 할 수 없습니다.");
        }

        photoBoardRepository.deleteById(boardId);
    }
    //endregion

    // 유저가 쓴 게시물 목록 가져오기 메소드
    @Transactional
    public List<PhotoBoardResponseDto> findUserBoardList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<PhotoBoardResponseDto> photoBoardResponseDtos = new ArrayList<>();
        List<PhotoBoard> photoBoards= photoBoardRepository.findAllByUserId(userDetails.getUser().getId());
        for(PhotoBoard photoBoard : photoBoards) {
            photoBoardResponseDtos.add(photoBoard.toDto());
        }
        return photoBoardResponseDtos;

    }

    // 유저가 좋아요 한 목록 가져오기 메소드
    @Transactional
    public List<PhotoBoardResponseDto> findUserLikePhotoBoardList(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        List<PhotoBoardResponseDto> photoBoardResponseDtos = new ArrayList<>();
        List<Likes> likes = likeRepository.findAllByUser(userDetails.getUser());
        HashMap<Long,Long> likeIdMap = new HashMap<>();
        for(Likes like : likes) {
            Long likeId = like.getId();
            Long likeBoardId = like.getBoardId();
            likeIdMap.put(likeId, likeBoardId);
            PhotoBoard photoBoard = photoBoardRepository.findById(likeIdMap.get(likeId)).orElseThrow(
                    ()-> new IllegalArgumentException("해당 게시물이 없습니다"));
            photoBoardResponseDtos.add(photoBoard.toDto());
        }
        return photoBoardResponseDtos;
    }


    private PhotoBoard findPhotoBoardById(
            Long boardId
    ) {
        PhotoBoard photoBoard = photoBoardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("해당 게시글이 없습니다.")
        );
        return photoBoard;
    }

}
