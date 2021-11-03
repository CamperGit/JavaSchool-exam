package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.services.ArticlesService;

import java.util.List;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articlesService.getAllArticles();
    }
}
