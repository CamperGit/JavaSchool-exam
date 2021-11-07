package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.repos.ArticlesRepo;
import ru.dataart.javaschoolexam.services.ArticlesService;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticlesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test to correctly obtain the first page of unfiltered articles")
    public void testGettingArticlesPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articles").param("pageNumber", "0"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/articles").param("pageNumber", "-1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"message\":\"Page index must not be less than zero!\"}"));
    }

    @Test
    @DisplayName("Testing filtering by non-existent section")
    public void testGettingFilteredArticlesPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/filterBySection")
                .param("sectionId","9999")
                .param("pageNumber", "0"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json("{\"message\":\"Not found section with selected id\"}"));

    }
}