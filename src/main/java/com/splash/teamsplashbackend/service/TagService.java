package com.splash.teamsplashbackend.service;


import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.dto.tag.TagRequestDto;
import com.splash.teamsplashbackend.dto.tag.TagResponseDto;
import com.splash.teamsplashbackend.model.PhotoBoard;
import com.splash.teamsplashbackend.model.Tag;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import com.splash.teamsplashbackend.repository.TagRepository;
import com.splash.teamsplashbackend.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final PhotoBoardRepository photoBoardRepository;
    //태그 저장
    public void saveTag(TagRequestDto tagRequestDto) {
        List<Tag> tags = tagRepository.findAll();
        TagValidator.checkTagName(tagRequestDto, tags);
        tagRepository.save(new Tag(tagRequestDto.getTagname()));
    }

    //태그 해당하는 게시물들 가져오기
    public TagResponseDto takeSpecificTagBoards(Long tagid) {
        Tag tag = tagRepository.findById(tagid).orElseThrow(
                ()->new IllegalArgumentException("찾으시는 태그가 없습니다"));
        String tagname = tag.getTagname();
        List<Tag> tags = tagRepository.findAll();
        HashMap<String,List<PhotoBoardResponseDto>> tagMap = new HashMap<>();
        for(Tag t : tags) {
            String tagName = tag.getTagname();
            List<PhotoBoardResponseDto> photoBoards = photoBoardRepository.findAllByTagname(tagName);
            tagMap.put(tagName, photoBoards);
        }
        return TagResponseDto.builder()
                .tagname(tagname)
                .photoboards(tagMap.get(tagname))
                .build();
    }


}
