package com.splash.teamsplashbackend.repository;

import com.splash.teamsplashbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String user);
}
