package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.Article;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String createArticlePage() {
        return "new";
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto) { //dto란 데이터 전송을 위한 오브젝트 Entity와는 구분해서 사용하는게좋음
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("id:%d", savedArticle.getId());
    }
}
