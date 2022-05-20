package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.posts.Posts;
import com.bgwb0007.intro.springboot.domain.posts.PostsRepository;
import com.bgwb0007.intro.springboot.web.dto.PostsReadResponseDto;
import com.bgwb0007.intro.springboot.web.dto.PostsSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    public PostsReadResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsReadResponseDto(entity);
    }


}
