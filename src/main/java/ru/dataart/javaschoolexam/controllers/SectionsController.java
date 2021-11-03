package ru.dataart.javaschoolexam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.services.SectionsService;

import java.util.List;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/sections")
public class SectionsController {
    private SectionsService sectionsService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionsService.getAllSections();
    }

    @Autowired
    public void setSectionsService(SectionsService sectionsService) {
        this.sectionsService = sectionsService;
    }
}
