package com.formation.TPFormationFavManager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "favorite")
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "category_id")
    @JoinColumn(nullable = false)
    private Category category;

    @Column(name = "Link")
    private String link;

    @Column(name = "Updated_at")
    private Date update;
}