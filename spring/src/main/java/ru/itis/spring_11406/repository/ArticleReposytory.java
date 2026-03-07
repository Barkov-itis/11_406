package ru.itis.spring_11406.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.spring_11406.models.Article;
import ru.itis.spring_11406.models.User;

public interface ArticleReposytory extends JpaRepository<Article, Long> {
    boolean existsByArticleIdAndLikesContaining(Long articleId, User user);
}
