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
    private Long artID;

    private String title;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private User author;

    @ManyToMany(mappedBy = "collections_arts")
    private List<UserCollection> userCollections = new ArrayList<>();

    @ManyToMany(mappedBy = "liked_arts")
    private List<Likes> likes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    private String description;

    private LocalDate publishDate;

    // Производим инициализайцию
    @PrePersist
    protected void init() {
        publishDate = LocalDate.now();
    }
}
