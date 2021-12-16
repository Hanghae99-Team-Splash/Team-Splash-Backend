package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByTagname(String tagname);
}
