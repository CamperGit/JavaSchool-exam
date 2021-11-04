package ru.dataart.javaschoolexam.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "sections")
public class Section {
    private Integer sectionId;
    private String name;
    @JsonBackReference
    private List<Article> articles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    public Integer getSectionId() {
        return sectionId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 250)
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    public List<Article> getArticles() {
        return articles;
    }

    public Section(String name, List<Article> articles) {
        this.name = name;
        this.articles = articles;
    }
}
