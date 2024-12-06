package com.cursework.kuroi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arts")
public class Art {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    private String description;

    private LocalDate publishDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "art")
    private List<_Likes> likes = new ArrayList<>();


    // Производим инициализайцию
    @PrePersist
    protected void init() {
        publishDate = LocalDate.now();
    }

}
