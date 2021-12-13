package com.splash.teamsplashbackend.model;


import com.splash.teamsplashbackend.utils.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@NoArgsConstructor
@Getter
@Entity
@Setter
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;


    @Column(unique = true)
    private Long kakaoId;
//  photoboard랑 매핑할 것
//    @OneToMany(mappedBy = "user")
//    List<PhotoBoard> photoBoards = new ArrayList<>();

    @Builder
    public User(String username, String password, String name, String nickname){
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.kakaoId = null;
    }

    public User(String username, String password, String name, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.nickname = name;
        this.kakaoId = kakaoId;
    }

}