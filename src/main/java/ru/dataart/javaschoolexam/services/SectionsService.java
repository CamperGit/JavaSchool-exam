package ru.dataart.javaschoolexam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.repos.SectionsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SectionsService {
    private final SectionsRepo sectionsRepo;

    public Section createNewSectionByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Section name cannot be null or empty");
        } else {
            Section section = new Section(name, new ArrayList<>());
            return sectionsRepo.save(section);
        }
    }

    public Section saveSection(Section section) {
        return sectionsRepo.save(section);
    }

    public List<Section> saveSections(List<Section> sections) {
        return sectionsRepo.saveAll(sections);
    }

    public Optional<Section> findSectionById(Integer id) {
        return sectionsRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllSections() {
        return sectionsRepo.findAll();
    }

    public void deleteSection(Section section) {
        sectionsRepo.delete(section);
    }

    public void deleteAllSection() {
        sectionsRepo.deleteAll();
    }

    public long getCountOfRows() {
        return sectionsRepo.count();
    }
}
