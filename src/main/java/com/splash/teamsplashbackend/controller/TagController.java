package com.splash.teamsplashbackend.controller;



import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.dto.tag.TagResponseDto;
import com.splash.teamsplashbackend.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @ApiOperation(value = "태그 등록")
    @PostMapping("/tag")
    public String registTag(@RequestBody TagRequestDto tagRequestDto) {
        tagService.saveTag(tagRequestDto);
        return "Complete to Regist Tag";
    }

    @ApiOperation(value = "태그에 따른 게시물 가져오기")
    @GetMapping("/tag/{tagname}")
    public TagResponseDto getSpecificTagBoards(@PathVariable String tagname) {
        return tagService.takeSpecificTagBoards(tagname);
    }

}
