package com.splash.teamsplashbackend.repository;


import com.splash.teamsplashbackend.model.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard,Long> {
    List<PhotoBoard> findAllByTagname(String tagname);

    @Modifying
    @Query("update PhotoBoard b set b.views = b.views + 1 where b.id = :id")
    int updateView(Long id);

    @Modifying
    @Query("update PhotoBoard a set a.likeCnt = a.likeCnt + 1 where a.id = :id")
    int upLikeCnt(Long id);

    @Modifying
    @Query("update PhotoBoard a set a.likeCnt = a.likeCnt - 1 where a.id = :id")
    int downLikeCnt(Long id);
}
