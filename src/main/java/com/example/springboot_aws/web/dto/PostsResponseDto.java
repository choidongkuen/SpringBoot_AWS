package com.example.springboot_aws.web.dto;


import com.example.springboot_aws.domain.posts.Posts;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PostsResponseDto {


    private Long id;

    private String title;

    private String content;

    private String author;


    public PostsResponseDto(Posts posts) {

        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }

}
