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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    private LocalDate orderDate = LocalDate.now();

    private String orderStatus;

    private Long price;

    @ManyToMany
    @JoinTable(
            name = "order_art",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "art_id")
    )
    private List<Art> arts = new ArrayList<>();

    @ManyToOne
    private User user;
}
