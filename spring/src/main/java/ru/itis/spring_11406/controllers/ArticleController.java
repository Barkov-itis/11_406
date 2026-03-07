package ru.itis.spring_11406.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.spring_11406.dto.ArticleDto;
import ru.itis.spring_11406.dto.ArticleForm;
import ru.itis.spring_11406.services.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/users/{userId}/articles")
    public String getArticleOfUser(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("articles", articleService.getByUser(userId));
        return "article_page";
    }

    @PostMapping("/users/{userId}/articles")
    @ResponseBody
    public ArticleDto addArticle(@PathVariable("userId") String userId,
                                 @ModelAttribute ArticleForm articleForm){
        System.out.println(articleForm.getName());
        System.out.println(articleForm.getText());
        return articleService.addArticle(Long.valueOf(userId),articleForm);
    }

    @PostMapping("/users/{userId}/articles/{articleId}/like")
    @ResponseBody
    public Object like(@PathVariable("userId") Long userId,
                       @PathVariable("articleId") Long articleId) {
        return articleService.like(userId, articleId);
    }

}
