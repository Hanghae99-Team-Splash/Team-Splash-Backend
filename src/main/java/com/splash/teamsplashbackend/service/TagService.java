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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final PhotoBoardRepository photoBoardRepository;
    //태그 저장
    @Transactional
    public void saveTag(TagRequestDto tagRequestDto) {
        List<Tag> tags = tagRepository.findAll();
        TagValidator.checkTagName(tagRequestDto, tags);
        tagRepository.save(new Tag(tagRequestDto.getTagname()));
    }

    //태그 해당하는 게시물들 가져오기
    @Transactional
    public TagResponseDto takeSpecificTagBoards(Long tagid) {
        Tag tag = tagRepository.findById(tagid).orElseThrow(
                ()->new IllegalArgumentException("찾으시는 태그가 없습니다"));
        String tagname = tag.getTagname();
        List<Tag> tags = tagRepository.findAll();
        List<PhotoBoardResponseDto> photoBoardResponseDtos = new ArrayList<>();
        HashMap<String,List<PhotoBoardResponseDto>> tagMap = new HashMap<>();
        for(Tag t : tags) {
            String tagName = t.getTagname();
            List<PhotoBoard> photoBoards = photoBoardRepository.findAllByTagname(tagName);
            for(int i=0; i<photoBoards.size(); i++) {
                photoBoardResponseDtos.add(photoBoards.get(i).toEntity());
            }
            tagMap.put(tagName, photoBoardResponseDtos);
        }
        return TagResponseDto.builder()
                .tagname(tagname)
                .photoboards(tagMap.get(tagname))
                .build();
    }


}
