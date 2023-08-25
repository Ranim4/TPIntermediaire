package com.formation.TPFormationFavManager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "label")
    private String label;

   // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Favorite> favorites = new ArrayList<>();
}
