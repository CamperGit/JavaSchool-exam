package ru.dataart.javaschoolexam.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;

@Repository
public interface ArticlesRepo extends JpaRepository<Article, Integer> {
    Page<Article> findAll(Pageable pageable);
    Page<Article> getArticlesBySection(Pageable pageable, Section section);
}
