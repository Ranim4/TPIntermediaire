package com.formation.TPFormationFavManager.service;

import com.formation.TPFormationFavManager.dto.CategoryListItem;
import com.formation.TPFormationFavManager.dto.FavDefinition;
import com.formation.TPFormationFavManager.dto.FavItem;
import com.formation.TPFormationFavManager.dto.FavListItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterFavoriteService {

    List<FavListItem> findAll();
    List<FavListItem> sortByCategory();
    List<FavListItem> sortByDate();
    List<FavListItem> filterByCategory(String category);
    ResponseEntity<String> removeSelectedFav(List <Long> idsToDelete);
    FavItem addFavorite (Long idCategory, FavDefinition favDefinition);
    Long totalNumberFav();
    Long slectedNumberFav(List <Long> idsSelectedFav);
    List<CategoryListItem> findAllCategory();
}
