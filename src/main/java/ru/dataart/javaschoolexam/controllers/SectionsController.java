package ru.dataart.javaschoolexam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.services.SectionsService;

import java.util.List;

@CrossOrigin(origins = "${crossOrigin.url}", maxAge = 3600)
@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionsController {
    private final SectionsService sectionsService;

    @GetMapping
    public ResponseEntity<?> getAllSections() {
        return ResponseEntity.ok(sectionsService.getAllSections());
    }

    @PostMapping
    public ResponseEntity<?> createNewSectionByName(@RequestParam String name) {
        return ResponseEntity.ok(sectionsService.createNewSectionByName(name));
    }

    @DeleteMapping("/{id}")
    public void deleteSectionById(@PathVariable("id") Section section) {
        sectionsService.deleteSection(section);
    }
}
