package com.example.springboot_aws.service.posts;

import com.example.springboot_aws.domain.posts.Posts;
import com.example.springboot_aws.domain.posts.PostsRepository;
import com.example.springboot_aws.exception.PostsException;
import com.example.springboot_aws.type.ErrorCode;
import com.example.springboot_aws.web.dto.PostsResponseDto;
import com.example.springboot_aws.web.dto.PostsSaveRequestDto;
import com.example.springboot_aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.springboot_aws.type.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service

public class PostsService {

    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto request) {

        return postsRepository.save(request.toEntity(request)).getId();

    };

    public PostsResponseDto findById(Long id) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new PostsException(POST_NOT_FOUND));

        return new PostsResponseDto(posts);
    }
    @Transactional // 영속성 컨텍스트 시작
    public Long update(Long id, PostsUpdateRequestDto request) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new PostsException(POST_NOT_FOUND));

        posts.update(request.getContent(),request.getTitle());

        return posts.getId();
    }
}
