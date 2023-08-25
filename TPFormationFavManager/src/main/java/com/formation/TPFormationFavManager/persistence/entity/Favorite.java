package com.formation.TPFormationFavManager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Favorite")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Category category;

    @Column(name = "Link")
    private String link;

    @Column(name = "Updated_at")
    private Date update;
}