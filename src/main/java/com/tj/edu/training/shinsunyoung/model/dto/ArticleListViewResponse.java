package com.tj.edu.training.shinsunyoung.model.dto;

import com.tj.edu.training.shinsunyoung.model.Article;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;


    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
