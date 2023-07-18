package com.tj.edu.training.shinsunyoung.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.model.dto.UpdateArticleRequest;
import com.tj.edu.training.shinsunyoung.repository.ArticleRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ObjectMapper om;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
    @Order(3)
    @DisplayName("addArticle: 아티클 글 추가에 성공한다.")
    @Test
    void addArticle() throws Exception {
        // 1. give
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest request = AddArticleRequest.builder()
                .title(title)
                .content(content)
                .build();

        // java 자료형을 ObjectManager를 통해 json string 형태로 바꾸어줌
        final String requestBody = om.writeValueAsString(request);

        // 2. when(postman에서 post api를 호출한 것과 같은 효과)
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE) // request data를 json으로 받겠다.
                .content(requestBody)); // request data -> 여기서는 AddArticleRequest


        // 3. then
        resultActions.andExpect(status().isCreated());

        List<Article> articles = articleRepository.findAll();

        assertThat(articles.size()).isEqualTo(4);
        assertThat(articles.get(3).getTitle()).isEqualTo(title);
        assertThat(articles.get(3).getContent()).isEqualTo(content);
    }
    @Order(1)
    @DisplayName("findAllArticles: 아티클 목록 조회에 성공한다.")
    @Test
    void findAllArticles() throws Exception {
        // 1. given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        articleRepository.save(Article.builder()
                    .title(title)
                    .content(content)
                    .build());

        // 2. when(postman에서 get api를 호출한 것과 같은 효과)
        ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE)); // data를 json으로 받겠다.

        // 3. then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[3].content").value(content))
                .andExpect(jsonPath("$[3].title").value(title));
    }

    @Order(2)
    @DisplayName("findArticle: 아티클 상세 조회에 성공한다.")
    @Test
    void findArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc
                .perform(get(url, savedArticle.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @Order(4)
    @DisplayName("deleteArticle: 아티클 삭제에 성공한다.")
    @Test
    void deleteArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final Long id = 2L;
        // when
        final ResultActions resultActions = mockMvc
                .perform(delete(url, id));
        // then
        resultActions
                .andExpect(status().isOk());
    }

    @Order(5)
    @DisplayName("updateArticle: 아티클 수정에 성공한다.")
    @Test
    void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final Long id = 3L;
        final String title = "title";
        final String content = "content";
        final UpdateArticleRequest request = UpdateArticleRequest.builder()
                .title(title)
                .content(content)
                .build();
        // java 자료형을 ObjectManager를 통해 json string 형태로 바꾸어줌
        final String requestBody = om.writeValueAsString(request);

        // when
        final ResultActions resultActions = mockMvc
                .perform(put(url, requestBody));
        // then
        resultActions.andExpect(status().isOk());
        List<Article> articles = articleRepository.findAll();
        assertThat(articles.get(2).getTitle()).isEqualTo(title);
        assertThat(articles.get(2).getContent()).isEqualTo(content);
    }
}