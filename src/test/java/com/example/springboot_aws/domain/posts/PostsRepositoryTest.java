package com.example.springboot_aws.domain.posts;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

//    @AfterEach // 모든 Test 메소드 후 실행
//    public void cleanUp() {
//        postsRepository.deleteAll();
//    }

    @Test
    @DisplayName("getPosts")
    void 게시글_불러오기(){

        // given
        String title = "테스트 게시글1";
        String content = "테스트 본문1";

        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("danaver12@daum.net")
                        .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getTitle()).isEqualTo(title);
     }

     @Test
     @DisplayName("BaseEntity Test")
     void BaseEntityTest(){

         // given
         LocalDateTime now = LocalDateTime.now();
         postsRepository.save(Posts.builder()
                         .title("title")
                         .content("content")
                         .author("danaver12@daum.net")
                         .build());
         // when
         List<Posts> postsLists = postsRepository.findAll();

         // then
         Posts posts = postsLists.get(0);

         System.out.println(">>>>> createdDate = " + posts.getCreatedDt());
         System.out.println(">>>>> modifiedDate = " + posts.getUpdatedDt());


         posts.update("content[수정]","title[수정]");


         System.out.println(">>>>> modifiedDate = " + posts.getUpdatedDt());

     }
}