package com.splash.teamsplashbackend.controller;


import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/api/tag")
    public String registTag(@RequestBody TagRequestDto tagRequestDto) {
        tagService.saveTag(tagRequestDto);
        return "Complete to Regist Tag";
    }

}
