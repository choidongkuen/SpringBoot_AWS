package com.example.springboot_aws.web;

import com.example.springboot_aws.service.posts.PostsService;
import com.example.springboot_aws.web.dto.PostsResponseDto;
import com.example.springboot_aws.web.dto.PostsSaveRequestDto;
import com.example.springboot_aws.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// @WebMvcTest 를 이용한 테스트(Web Layer)
// @MockBean을 통해 repository 까지 충분히 구현은 가능

@WebMvcTest(PostsApiController.class)
class PostsApiControllerTest {


    @MockBean
    PostsService postsService;


    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("PostPostsSuccessTest")
    void 등록() {

        // given
        given(postsService.save(any()))
                .willReturn(2L);
        // when
        try {
            mockMvc.perform(post("/api/v1/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(
                            PostsSaveRequestDto.builder()
                                               .author("danaver12@daum.net")
                                               .title("제목1")
                                               .content("내용1")
                                               .build()
                    ))).andExpect(status().isOk())
                    .andExpect(content().string(String.valueOf(2)))
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // then
    }

    @Test
    @DisplayName("FindPostsSuccessTest")
    void 조회() {

        // given
        given(postsService.findById(anyLong()))
                .willReturn(PostsResponseDto.builder()
                        .id(1L)
                        .title("제목")
                        .content("내용")
                        .author("danaver12@daum.net")
                        .build()
                );

        // when

        try {
            mockMvc.perform(get("/api/v1/posts/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.title").value("제목"))
                    .andExpect(jsonPath("$.content").value("내용"))
                    .andExpect(jsonPath("$.author").value("danaver12@daum.net"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // then
     }

     @Test
     @DisplayName("UpdatePostsSuccessTest")
     void 수정(){

         // given
         String title = "제목";
         String content = "내용";

         given(postsService.update(anyLong(),any()))
                 .willReturn(1L);
         // when


         try {
             mockMvc.perform(put("/api/v1/posts/1")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(
                             PostsUpdateRequestDto.builder()
                                     .title(title)
                                     .content(content)
                                     .build()
                     ))).andExpect(status().isOk())
                     .andExpect(content().string(String.valueOf(1L)))
                     .andDo(print());

         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         // then
      }


}