package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByTagname(String tagname);
}
