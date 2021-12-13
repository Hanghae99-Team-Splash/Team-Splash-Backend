package com.splash.teamsplashbackend.controller;



import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.dto.tag.TagResponseDto;
import com.splash.teamsplashbackend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/tag")
    public String registTag(@RequestBody TagRequestDto tagRequestDto) {
        tagService.saveTag(tagRequestDto);
        return "Complete to Regist Tag";
    }

    @GetMapping("/tag/{tagid}")
    public TagResponseDto getSpecificTagBoards(@PathVariable Long tagid) {
        return tagService.takeSpecificTagBoards(tagid);
    }

}
