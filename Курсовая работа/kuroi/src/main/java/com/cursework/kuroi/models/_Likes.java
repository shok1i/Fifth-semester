package com.cursework.kuroi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_likes")
public class _Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeID;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "LikeToArt",
            joinColumns = { @JoinColumn(name = "artID") },
            inverseJoinColumns = { @JoinColumn(name = "likeID") }
    )
    private List<Art> liked_arts = new ArrayList<>();
}