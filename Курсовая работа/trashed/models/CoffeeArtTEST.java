package com.cursework.kuroi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coffee_arts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeArtTEST {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "coffeeArt")
    private List<TagTEST> tagTESTS;

    @Column(name = "likes")
    private Long likes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image image;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private LocalDateTime createdAt;

    @PrePersist
    public void init() {
        createdAt = LocalDateTime.now();
    }
}
