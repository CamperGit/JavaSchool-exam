package ru.dataart.javaschoolexam.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dataart.javaschoolexam.entities.Article;

@Repository
public interface ArticlesRepo extends JpaRepository<Article, Integer> {
}
