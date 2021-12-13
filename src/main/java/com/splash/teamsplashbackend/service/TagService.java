package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.model.Tag;
import com.splash.teamsplashbackend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    //태그 저장
    public void saveTag(TagRequestDto tagRequestDto) {
        tagRepository.save(new Tag(tagRequestDto.getTagname()));
    }

}
