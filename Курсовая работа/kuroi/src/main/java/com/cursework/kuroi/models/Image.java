package com.cursework.kuroi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageID;

    private String path;

    private String contentType;

    private Long size;

    @Lob
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private Art art;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn
    private User user;
}
