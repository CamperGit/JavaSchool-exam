package ru.dataart.javaschoolexam.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dataart.javaschoolexam.entities.Section;

@Repository
public interface SectionsRepo extends JpaRepository<Section, Integer> {
}
