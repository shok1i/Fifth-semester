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
@Table(name = "users_likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeID;

    @ManyToMany(cascade = { CascadeType.REFRESH })
    @JoinTable(
            name = "LikeToUser",
            joinColumns = { @JoinColumn(name = "userID") },
            inverseJoinColumns = { @JoinColumn(name = "likeID") }
    )
    private List<User> liked_users = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.REFRESH })
    @JoinTable(
            name = "LikeToArt",
            joinColumns = { @JoinColumn(name = "artID") },
            inverseJoinColumns = { @JoinColumn(name = "likeID") }
    )
    private List<Art> liked_arts = new ArrayList<>();


    // Добавляем время лайка
    private LocalDate likeDate = LocalDate.now();
}