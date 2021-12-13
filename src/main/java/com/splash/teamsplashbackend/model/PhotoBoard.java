package com.splash.teamsplashbackend.model;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.utils.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoBoard extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @ColumnDefault("0")
    private int views;

    @Column(nullable = false)
    private String tagname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void update(PhotoBoardRequestDto photoBoardRequestDto) {
        this.location = photoBoardRequestDto.getLocation();
        this.tagname = photoBoardRequestDto.getTagname();
        this.description = photoBoardRequestDto.getDescription();
    }

}
