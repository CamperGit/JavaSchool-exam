package ru.dataart.javaschoolexam.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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