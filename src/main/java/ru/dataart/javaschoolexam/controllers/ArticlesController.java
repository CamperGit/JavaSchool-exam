package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.services.ArticlesService;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticlesService articlesService;

    @PostMapping
    public ResponseEntity<?> createArticleByZipFile(@RequestParam("file") MultipartFile file, Integer sectionId) {
        return ResponseEntity.ok(articlesService.createArticleByZip(file, sectionId));
    }

    @GetMapping
    public ResponseEntity<?> getArticlesPage(@RequestParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(articlesService.getArticlesPage(pageNumber));
    }

    @GetMapping("/filterBySection")
    public ResponseEntity<?> getArticlesPageBySection(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("sectionId") Integer sectionId) {
        return ResponseEntity.ok(articlesService.getArticlesPageBySection(pageNumber, sectionId));
    }

    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable("id") Article article) {
        articlesService.deleteArticle(article);
    }
}
