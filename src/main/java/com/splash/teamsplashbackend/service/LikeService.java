package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.config.UserDetailsImpl;
import com.splash.teamsplashbackend.model.Likes;
import com.splash.teamsplashbackend.model.PhotoBoard;
import com.splash.teamsplashbackend.repository.LikeRepository;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PhotoBoardRepository photoBoardRepository;

    //좋아요
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

}
