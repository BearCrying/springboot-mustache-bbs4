package com.mustache.bbs4.domain.entity;

import com.mustache.bbs4.domain.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "article")
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public static ArticleDto of(Article article) { // article 에서 dto로 보내기
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

}
