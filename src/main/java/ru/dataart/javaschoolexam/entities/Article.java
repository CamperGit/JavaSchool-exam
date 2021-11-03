package ru.dataart.javaschoolexam.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "articles")
public class Article {
    private Integer articleId;
    private String title;
    private String text;
    private Timestamp dateOfCreation;
    private Section section;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    public Integer getArticleId() {
        return articleId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 250)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 1000)
    public String getText() {
        return text;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    public Section getSection() {
        return section;
    }

    public Article(String title, String text, Timestamp dateOfCreation, Section section) {
        this.title = title;
        this.text = text;
        this.dateOfCreation = dateOfCreation;
        this.section = section;
    }
}
