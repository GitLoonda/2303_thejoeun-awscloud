package com.tj.edu.training.shinsunyoung.service;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.AddArticleRequest;
import com.tj.edu.training.shinsunyoung.model.dto.UpdateArticleRequest;
import com.tj.edu.training.shinsunyoung.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    // article 저장
    public Article save(AddArticleRequest request) {
//        return articleRepository.save(new Article(request.getTitle(), request.getContent()));
        return articleRepository.save(request.toEntity());
    }

    // article 리스트 불러오기
    public List<Article> getArticlesAll() {
        return articleRepository.findAll();
    }

    // article 상세 보기
    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // article 지우기
    public void deleteArticle(Long id) {
        if(getArticle(id) != null) {
            articleRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("not found : " + id);
        }
    }

    // article 수정하기
    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
