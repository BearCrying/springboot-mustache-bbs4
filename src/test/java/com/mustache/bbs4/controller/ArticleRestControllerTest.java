package com.mustache.bbs4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs4.domain.dto.ArticleAddRequest;
import com.mustache.bbs4.domain.dto.ArticleAddResponse;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc; // MockMvc를 주입받아 사용함.
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ArticleService articleService;


    @Test
    @DisplayName("해당 id의 글이 조회가 잘 되는지")
    void findSingle() throws Exception {
        Long id = 1l;

        given(articleService.getArticleById(id))
                .willReturn(new ArticleDto(1l, "첫번째 글", "내용입니다.")); // dto로 값 넘겨줌

        int articleId = 1;
        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url)) //해당 URL에 요청을 해 값을 반환함
                .andExpect(status().isOk()) //perform에서 발생한 요청에 대한 어떤 응답값을 가져야하는지 지정함.
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(id); // 메소드 실행 했는지 검증해줌
    }


    @Test
    @DisplayName("글 등록이 잘 되는지")
    void add() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목입니다", "내용입니다."); //entity로 바꾸어줌

        given(articleService.add(any())).willReturn(new ArticleAddResponse(1l, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
    }
}