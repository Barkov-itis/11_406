package ru.itis.spring_11406.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.spring_11406.dto.ArticleDto;
import ru.itis.spring_11406.dto.ArticleForm;
import ru.itis.spring_11406.models.Article;
import ru.itis.spring_11406.models.User;
import ru.itis.spring_11406.repository.ArticleReposytory;
import ru.itis.spring_11406.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ArticleReposytory articleReposytory;

    @Override
    public List<ArticleDto> getByUser(Long id) {
        Optional<User> user = usersRepository.findById(id);
        List<Article> articlesOfUser = user.get().getCreatedArticles();
        return ArticleDto.articleList(articlesOfUser);
    }

    @Override
    public ArticleDto addArticle(Long userId, ArticleForm articleForm) {
        Optional<User> author = usersRepository.findById(userId);
        Article newArticle = Article.builder()
                .text(articleForm.getText())
                .name(articleForm.getName())
                .author(author.get())
                .build();
        articleReposytory.save(newArticle);
        return ArticleDto.from(newArticle);
    }

    @Override
    public ArticleDto like(Long userId, Long articleId) {
        Optional<User> user = usersRepository.findById(userId);
        Optional<Article> article = articleReposytory.findById(articleId);
        if (articleReposytory.existsByArticleIdAndLikesContaining(articleId, user.get())) {
            article.get().getLikes().remove(user.get());
        }
        else {
            article.get().getLikes().add(user.get());
        }
        System.out.println("***************");
        articleReposytory.save(article.get());
        return ArticleDto.from(article.get());
    }
}
