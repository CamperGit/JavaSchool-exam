package ru.dataart.javaschoolexam.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.services.ArticlesService;
import ru.dataart.javaschoolexam.services.SectionsService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtils implements CommandLineRunner {
    private final SectionsService sectionsService;
    private final ArticlesService articlesService;

    @Override
    public void run(String... args) throws Exception {
        long countOfSessions = sectionsService.getCountOfRows();
        long countOfArticles = articlesService.getCountOfRows();

        if (countOfSessions == 0 && countOfArticles == 0) {
            Section sportSection = new Section("Спорт", new ArrayList<>());
            Section financesSection = new Section("Финансы", new ArrayList<>());

            sectionsService.saveSections(List.of(sportSection, financesSection));

            Article sportArticle1 = new Article();
            sportArticle1.setTitle("Россия выиграла в чемпионате мира по футболу!");
            sportArticle1.setText("Ха, поверили? Значит у вас есть надежда...");
            sportArticle1.setDateOfCreation(Timestamp.from(Instant.now()));
            sportArticle1.setSection(sportSection);

            Article sportArticle2 = new Article();
            sportArticle2.setTitle("Россия получила золотые медали в олимпиаде в Токио!");
            sportArticle2.setText("Наши спортсмены были невероятны и одержали победу в...");
            sportArticle2.setDateOfCreation(Timestamp.from(Instant.now()));
            sportArticle2.setSection(sportSection);


            Article financeArticle1 = new Article();
            financeArticle1.setTitle("Стабильность - признак мастерства!");
            financeArticle1.setText("Доллар вновь вырос на 2 рубля...");
            financeArticle1.setDateOfCreation(Timestamp.from(Instant.now()));
            financeArticle1.setSection(financesSection);
            articlesService.saveArticle(financeArticle1);

            articlesService.saveArticles(List.of(sportArticle1, sportArticle2));
        }
    }
}
