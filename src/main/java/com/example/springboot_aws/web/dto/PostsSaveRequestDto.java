package com.example.springboot_aws.web.dto;


import com.example.springboot_aws.domain.posts.Posts;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class PostsSaveRequestDto {

    private String author;

    private String title;

    private String content;

    public Posts toEntity(PostsSaveRequestDto dto) {

        return Posts.builder()
                    .author(dto.getAuthor())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .build();
    }
}
