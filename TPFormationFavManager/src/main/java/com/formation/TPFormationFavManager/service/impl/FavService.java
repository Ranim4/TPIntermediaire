package com.formation.TPFormationFavManager.service.impl;

import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import com.formation.TPFormationFavManager.service.InterFavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavService implements InterFavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public List<FavListItem> findAll() {
        return favoriteRepository.findAll()
                .stream()
                .map(f -> new FavListItem(f.getId(), f.getCategory(), f.getLink(), f.getUpdate()))
                .toList();
    }
}
