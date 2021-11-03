package ru.dataart.javaschoolexam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.repos.ArticlesRepo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticlesRepo articlesRepo;

    public Article saveArticle(Article article) {
        return articlesRepo.save(article);
    }

    public List<Article> saveArticles(List<Article> articles) {
        return articlesRepo.saveAll(articles);
    }

    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articlesRepo.findAll();
    }

    public void deleteArticle(Article article) {
        articlesRepo.delete(article);
    }

    public long getCountOfRows() {
        return articlesRepo.count();
    }
}
