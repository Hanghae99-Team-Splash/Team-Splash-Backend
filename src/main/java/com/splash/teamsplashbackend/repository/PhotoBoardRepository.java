package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.model.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard,Long> {
    List<PhotoBoardResponseDto> findAllByTagname(String tagname);


}
