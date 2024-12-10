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
@Table(name = "users_collections")
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionID;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "CollectionToArt",
            joinColumns = { @JoinColumn(name = "artID") },
            inverseJoinColumns = { @JoinColumn(name = "collectionID") }
    )
    private List<Art> collections_arts = new ArrayList<>();
}




