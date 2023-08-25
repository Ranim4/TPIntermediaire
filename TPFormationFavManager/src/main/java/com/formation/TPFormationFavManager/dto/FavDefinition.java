package com.formation.TPFormationFavManager.dto;

import com.formation.TPFormationFavManager.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavDefinition {
    private Long id;
    private Category category;
    private String link;
    private Date update;
}
