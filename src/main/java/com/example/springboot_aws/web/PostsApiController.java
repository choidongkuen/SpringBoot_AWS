package com.example.springboot_aws.web;


import com.example.springboot_aws.service.posts.PostsService;
import com.example.springboot_aws.web.dto.PostsResponseDto;
import com.example.springboot_aws.web.dto.PostsSaveRequestDto;
import com.example.springboot_aws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto request) {

        return postsService.save(request);

    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postsService.findById(id);

    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody PostsUpdateRequestDto request) {

        return postsService.update(id,request);
    }
}
