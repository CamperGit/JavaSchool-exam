package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.services.ArticlesService;

import java.util.List;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;

    @PostMapping
    public Article createArticleByZipFile(@RequestParam("file") MultipartFile file, Integer sectionId) {
        return articlesService.createArticleByZip(file, sectionId);
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articlesService.getAllArticles();
    }
}
