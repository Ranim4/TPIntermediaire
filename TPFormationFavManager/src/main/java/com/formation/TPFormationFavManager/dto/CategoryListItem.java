package com.formation.TPFormationFavManager.dto;

import com.formation.TPFormationFavManager.persistence.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListItem {
    private Long id;
    private String label;
//    private long references;
}
