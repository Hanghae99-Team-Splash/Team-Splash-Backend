package com.splash.teamsplashbackend.service;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.model.PhotoBoard;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoBoardService {
    private final PhotoBoardRepository photoBoardRepository;


    public Long uploadPhotoPost(
//            UserDetailsImpl userDetails,
            PhotoBoardRequestDto photoBoardRequestDto,
            MultipartFile multipartFile
    ) {
        if(multipartFile.getSize() == 0) {
            throw new NullPointerException("등록하려는 게시글에 이미지가 없습니다.");
        }

//        String imageUrl = s3Uploader.upload(multipartFile, imageDirName);
        PhotoBoard post = PhotoBoard.builder()
                        .img("imageUrl")
                        .location(photoBoardRequestDto.getLocation())
                        .description(photoBoardRequestDto.getLocation())
                        .tagname(photoBoardRequestDto.getTagname())
//                .user()
                        .build();
        photoBoardRepository.save(post);

        return post.getId();
    }

    public void editPhotoBoard(
            Long boardId,
//            User user,
            PhotoBoardRequestDto photoBoardRequestDto
    ) {
        PhotoBoard modifiedBoard = photoBoardRepository.findById(boardId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );

//        if (!modifiedBoard.getUser().getId().equals(user.getId()))
//            throw new IllegalArgumentException("작성자가 아니라 게시글을 수정 할 수 없습니다.");


        modifiedBoard.update(photoBoardRequestDto);

        photoBoardRepository.save(modifiedBoard);
    }

    public List<PhotoBoard> findAllPaging() {
        List<PhotoBoard> board = photoBoardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

//        return board.stream()
//                .map(
//                        s-> new BoardResponseDto(
//                                s.getId(),
//                                s.getNickname(),
//                                s.getContent(),
//                                s.getLocation(),
//                                s.getImage()))
//                .collect(Collectors.toList()
//                );
        return null;
    }

    public PhotoBoard findPhotoBoard(
            Long id
    ) {
        PhotoBoard photoBoard = photoBoardRepository.findById(id)
                .orElseThrow(
                        () -> new NullPointerException("찾으려는 게시글이 없습니다.")
                );
        return photoBoard;
    }

    public void deletePhotoBoard(Long boardId) {
        PhotoBoard board = photoBoardRepository.findById(boardId)
                .orElseThrow(
                        () -> new NullPointerException("삭제하려는 게시글이 없습니다.")
                );
//        if (!board.getUser().getId().equals(loginUserId)) {
//            throw new IllegalArgumentException("작성자가 아니라 게시글을 삭제 할 수 없습니다.");
//        }

        photoBoardRepository.deleteById(boardId);
    }
}
