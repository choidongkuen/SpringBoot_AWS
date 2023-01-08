package com.example.springboot_aws.web.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor

public class PostsUpdateRequestDto {

    private String title;

    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {

        this.title = title;

        this.content = content;
    }
}
