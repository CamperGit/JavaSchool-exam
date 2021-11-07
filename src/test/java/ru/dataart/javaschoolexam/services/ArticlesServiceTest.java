package ru.dataart.javaschoolexam.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.exceptions.NotZipFileException;
import ru.dataart.javaschoolexam.exceptions.WrongArticleFormatException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticlesServiceTest {
    @Autowired
    private ArticlesService articlesService;

    @Test
    @DisplayName("Testing throwing exception when loading file have not zip format")
    public void testLoadingRarFile() throws Exception {
        MultipartFile multipartFile = getMultipartFileByPath("articles/rarArticle.rar");
        assertThrows(NotZipFileException.class, ()-> articlesService.createArticleByZip(multipartFile, null));
    }

    @Test
    @DisplayName("Testing throwing exception when zip archive contains more then 1 file")
    public void testLoading2FilesInZip() throws Exception {
        MultipartFile multipartFile = getMultipartFileByPath("articles/2filesInZip.zip");
        assertThrows(WrongArticleFormatException.class, ()-> articlesService.createArticleByZip(multipartFile, null));
    }

    @Test
    @DisplayName("Testing throwing exception when article file have wrong name")
    public void testLoadingArticleWithWrongName() throws Exception {
        MultipartFile multipartFile = getMultipartFileByPath("articles/wrongName.zip");
        assertThrows(WrongArticleFormatException.class, ()-> articlesService.createArticleByZip(multipartFile, null));
    }

    @Test
    @DisplayName("Testing throwing exception when article doesn't have body")
    public void testLoadingArticleWithoutBody() throws Exception {
        MultipartFile multipartFile = getMultipartFileByPath("articles/nobody.zip");
        assertThrows(WrongArticleFormatException.class, ()-> articlesService.createArticleByZip(multipartFile, null));
    }

    @Test
    @DisplayName("Testing loading correct article")
    public void testLoadingCorrectArticle() throws Exception {
        MultipartFile multipartFile = getMultipartFileByPath("articles/correct.zip");
        Article articleByZip = articlesService.createArticleByZip(multipartFile, null);
        assertNotNull(articleByZip);
        assertEquals(articleByZip.getTitle(), "Title");
        assertEquals(articleByZip.getText(), "Text");
        assertNull(articleByZip.getSection());
    }

    private MultipartFile getMultipartFileByPath(String path) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        File file = new File(resource.getFile());
        InputStream stream =  new FileInputStream(file);
        String contentType = Files.probeContentType(file.toPath());
        return new MockMultipartFile("file", file.getName(), contentType, stream);
    }
}