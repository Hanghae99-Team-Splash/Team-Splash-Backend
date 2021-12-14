package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.model.Likes;
import com.splash.teamsplashbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByBoardIdAndUserId(Long boardId, Long userId);
    List<Likes> findAllByUser(User user);
}
