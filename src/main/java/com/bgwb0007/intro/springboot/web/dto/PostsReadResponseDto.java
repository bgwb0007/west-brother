package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsReadResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsReadResponseDto(Posts entity){
        this.id=entity.getId();
        this.title=entity.getTitle();
        this.content=entity.getContent();
        this.author=entity.getAuthor();
    }
}
