package com.cursework.kuroi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private _UserCollection userCollection;

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
