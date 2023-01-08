package com.example.springboot_aws.web;

import com.example.springboot_aws.domain.posts.Posts;
import com.example.springboot_aws.domain.posts.PostsRepository;
import com.example.springboot_aws.web.dto.PostsSaveRequestDto;
import com.example.springboot_aws.web.dto.PostsUpdateRequestDto;
import org.apache.coyote.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 전체 테스트

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest02 {

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        this.postsRepository.deleteAll();
    }


    @Test
    @DisplayName("PostPostsSuccessTest")
    void 등록() {
        // given

        String title = "제목";
        String content = "내용";

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                                                     .title(title)
                                                     .content(content)
                                                     .author("danaver12@daum.net")
                                                     .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(url, dto, Long.class);
        // then

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isGreaterThan(0L);

        List<Posts> postsList = postsRepository.findAll();

        assertThat(postsList.get(0).getContent())
                .isEqualTo(content);
        assertThat(postsList.get(0).getTitle())
                .isEqualTo(title);
    }

    @Test
    @DisplayName("FindPostsSuccessTest")
    void 조회(){
        // given

        // when
        // then
     }


     @Test
     @DisplayName("UpdatePostsSuccessTest")
     void 수정(){
         // given

         Posts posts = postsRepository.save(Posts.builder()
                         .title("제목")
                         .content("내용")
                         .author("danaver12@daum.net")
                         .build());

         Long id = 1L;
         PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                 .title("제목[수정]")
                 .content("내용[수정]")
                 .build();

         String url = "http://localhost:" + port + "/api/v1/posts/" + posts.getId();

         HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(dto);

         // when
         ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url,
                 HttpMethod.PUT, requestEntity, Long.class);
         // then

         assertThat(responseEntity.getBody()).isEqualTo(1L);
         assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

         List<Posts> postsList = postsRepository.findAll();

         assertThat(postsList.get(0).getTitle()).isEqualTo("제목[수정]");
      }


}