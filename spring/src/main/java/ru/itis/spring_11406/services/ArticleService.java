package ru.itis.spring_11406.services;

import ru.itis.spring_11406.dto.ArticleDto;
import ru.itis.spring_11406.dto.ArticleForm;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getByUser(Long id);

    ArticleDto addArticle(Long userId, ArticleForm articleForm);

    ArticleDto like(Long userId, Long articleId);
}
