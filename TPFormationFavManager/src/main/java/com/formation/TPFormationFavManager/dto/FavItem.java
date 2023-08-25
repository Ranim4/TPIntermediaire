package com.formation.TPFormationFavManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavItem {
    private Long id;
    private String category;
    private String link;
    private Date update;
}
