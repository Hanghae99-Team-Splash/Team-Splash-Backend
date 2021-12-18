package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.dto.likes.LikesResponseDto;
import com.splash.teamsplashbackend.model.Likes;
import com.splash.teamsplashbackend.repository.LikeRepository;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PhotoBoardRepository photoBoardRepository;

    //region 좋아요
    @Transactional
    public String clickLike(UserDetailsImpl userDetails, Long boardId) {
        Long userId = userDetails.getUser().getId();
        Optional<Likes> foundLike = likeRepository.findByBoardIdAndUserId(boardId, userId);
        if(foundLike.isPresent()) {
            likeRepository.deleteById(foundLike.get().getId());
            photoBoardRepository.downLikeCnt(boardId);
            return "Success Like Off";
        }
        else {
            likeRepository.save(new Likes(boardId, userDetails.getUser()));
            photoBoardRepository.upLikeCnt(boardId);
            return "Success Like On";
        }
    }
    //endregion

    //region 한 게시물에 좋아요 누른 유저들의 아이디 값 받아오기
    @Transactional
    public LikesResponseDto getOnePostingLikesUserIds(@PathVariable Long boardId) {
        List<Likes> likesList = likeRepository.findAllByBoardId(boardId);
        List<Long> userIds = new ArrayList<>();
       for (int i=0; i<likesList.size(); i++) {
           userIds.add(likesList.get(i).getUser().getId());
       }
        return LikesResponseDto.builder()
                .userIds(userIds)
                .build();
    }
    //endregion

}
