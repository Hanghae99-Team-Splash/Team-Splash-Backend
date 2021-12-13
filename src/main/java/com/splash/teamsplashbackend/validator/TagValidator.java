package com.splash.teamsplashbackend.validator;


import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagValidator {
    public static void checkTagName(TagRequestDto tagRequestDto, List<Tag> tags) {
        for(Tag tag : tags) {
            if(tag.getTagname().equals(tagRequestDto.getTagname())) {
                throw new IllegalArgumentException("태그명은 고유 값 입니다. 중복된 태그를 저장하실 수 없습니다.");
            }
        }
    }




}
