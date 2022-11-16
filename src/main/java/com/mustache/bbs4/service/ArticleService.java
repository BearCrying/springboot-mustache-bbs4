package com.mustache.bbs4.service;


import com.mustache.bbs4.domain.dto.ArticleAddRequest;
import com.mustache.bbs4.domain.dto.ArticleAddResponse;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticleById(Long id) { // 리포에서 받은 값 DTO로 넘겨주는 메소드
        Optional<Article> optArticle = articleRepository.findById(id); // 리포에서 가져온 값이 article에 들어감
        ArticleDto articleDto = Article.of(optArticle.get()); //article of(메소드)는 articleDto로 전달
        return articleDto;
    }

    public ArticleAddResponse add(ArticleAddRequest dto) {
        Article article = dto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}