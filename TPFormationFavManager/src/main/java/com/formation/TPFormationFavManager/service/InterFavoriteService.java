package com.formation.TPFormationFavManager.service;

import com.formation.TPFormationFavManager.dto.FavListItem;

import java.util.List;

public interface InterFavoriteService {

    List<FavListItem> findAll();
}
