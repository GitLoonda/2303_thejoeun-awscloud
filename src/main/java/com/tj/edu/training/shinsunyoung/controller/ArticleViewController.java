package com.tj.edu.training.shinsunyoung.controller;

import com.tj.edu.training.shinsunyoung.model.Article;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleListViewResponse;
import com.tj.edu.training.shinsunyoung.model.dto.ArticleResponse;
import com.tj.edu.training.shinsunyoung.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articleResponseList = articleService.getArticlesAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articleResponseList);
        return "articleList";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", new ArticleListViewResponse(article));
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(Model model) {

        return "newArticle";
    }

}
