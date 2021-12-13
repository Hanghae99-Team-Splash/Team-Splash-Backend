package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.model.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard,Long> {
}
