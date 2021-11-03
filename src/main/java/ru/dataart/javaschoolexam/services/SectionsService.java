package ru.dataart.javaschoolexam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.repos.SectionsRepo;

import java.util.List;

@Service
@Transactional
public class SectionsService {
    private SectionsRepo sectionsRepo;

    public Section saveSection(Section section) {
        return sectionsRepo.save(section);
    }

    public List<Section> saveSections(List<Section> sections) {
        return sectionsRepo.saveAll(sections);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllSections() {
        return sectionsRepo.findAll();
    }

    public void deleteSection(Section section) {
        sectionsRepo.delete(section);
    }

    public long getCountOfRows() {
        return sectionsRepo.count();
    }

    @Autowired
    public void setSectionsRepo(SectionsRepo sectionsRepo) {
        this.sectionsRepo = sectionsRepo;
    }
}
