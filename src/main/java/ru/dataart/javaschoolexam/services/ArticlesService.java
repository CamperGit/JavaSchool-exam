package ru.dataart.javaschoolexam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.repos.ArticlesRepo;
import ru.dataart.javaschoolexam.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticlesRepo articlesRepo;
    private final SectionsService sectionsService;
    private final FileUtils fileUtils;
    private static final String ARTICLES_PATH = File.separator + "articles";

    public Article createArticleByZip(MultipartFile file, Integer sectionId) {
        Optional<Section> section = sectionsService.findSectionById(sectionId);
        try {
            File zipFile = fileUtils.convertMultipartFileToFile(file);
            List<File> unpackedFiles = fileUtils.unpackZipFileToDirectory(zipFile, ARTICLES_PATH);
            if (unpackedFiles.size() != 1) {
                throw new IllegalStateException();
            }
            File fileFromZip = unpackedFiles.get(0);
            if (fileFromZip.getName().equals("article.txt")) {
                List<String> strings = Files.readAllLines(fileFromZip.toPath());
                if (strings.size() > 2) {
                    String title = strings.get(0);
                    StringBuilder articleBodyBuilder = new StringBuilder();
                    for (int i = 1; i < strings.size(); i++ ) {
                        articleBodyBuilder.append(strings.get(i));
                    }
                    String articleBody = articleBodyBuilder.toString();
                    Article article = new Article();
                    article.setTitle(title);
                    article.setText(articleBody);
                    article.setDateOfCreation(Timestamp.from(Instant.now()));
                    section.ifPresent(article::setSection);
                    return articlesRepo.save(article);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
