package com.splash.teamsplashbackend.model;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.utils.TimeCalculator;
import com.splash.teamsplashbackend.utils.Timestamped;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    @Column
    private int likeCnt;

    @Column(nullable = false)
    private String tagname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void update(PhotoBoardRequestDto photoBoardRequestDto) {
        this.location = photoBoardRequestDto.getLocation();
        this.tagname = photoBoardRequestDto.getTagname();
        this.description = photoBoardRequestDto.getDescription();
    }



    public PhotoBoardResponseDto toEntity() {
        return PhotoBoardResponseDto.builder()
                .boardId(id)
                .userId(user.getId())
                .img(img)
                .nickname(user.getNickname())
                .location(location)
                .tagname(tagname)
                .likeCnt(likeCnt)
                .description(description)
                .modifiedAt(TimeCalculator.timecalculator(modifiedAt))
                .build();
    }
}
