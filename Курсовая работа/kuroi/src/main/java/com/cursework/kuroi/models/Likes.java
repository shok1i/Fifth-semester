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
@Table(name = "likes")
public class Likes {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Art art;

    private LocalDate likeDate;

    // Производим инициализацию
    @PrePersist
    private void init() {
        likeDate = LocalDate.now();
    }
}
