package com.formation.TPFormationFavManager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Category")
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
}
