package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.services.SectionsService;

import java.util.List;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
public class SectionsController {
    private final SectionsService sectionsService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionsService.getAllSections();
    }

    @PostMapping
    public Section createNewSectionByName(@RequestParam String name) {
        return sectionsService.createNewSectionByName(name);
    }
}
