package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleResponse;
import com.tj.edu.training.shinsunyoung.model.dto.UpdateArticleRequest;
import com.tj.edu.training.shinsunyoung.repository.ArticleRepository;
import com.tj.edu.training.shinsunyoung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article article = articleService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articleList = articleService.getArticlesAll();

//  stream을 이용한 방식
//        List<ArticleResponse> articleResponseList = articleList.stream()
//                .map(ArticleResponse::new)
//                .toList();

//  옛날에 쓰던 방식
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponse articleResponse = new ArticleResponse(article);
            articleResponseList.add(articleResponse);
        }
        Collections.sort(articleResponseList,
                Comparator.comparingLong(ArticleResponse::getId).reversed());

//        return ResponseEntity.status(HttpStatus.OK)
        return ResponseEntity.ok().body(articleResponseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = articleService.getArticle(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable long id) {
        articleService.deleteArticle(id);

        return ResponseEntity.ok()
                .body(new String("삭세한 블로그 글 번호 id: " + id));
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        articleService.updateArticle(id, request);

        return ResponseEntity.ok()
                .body(new String("수정한 블로그 글 번호 id: " + id));
    }

}
