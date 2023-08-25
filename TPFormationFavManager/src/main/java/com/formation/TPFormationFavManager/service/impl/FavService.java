package com.formation.TPFormationFavManager.service.impl;

import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import com.formation.TPFormationFavManager.service.InterFavoriteService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    @Override
    public List<FavListItem> sortByCategory() {
        return findAll().stream()
                .sorted((item1, item2)->item1.getCategory().getLabel().
                        compareTo(item2.getCategory().getLabel()))
                .toList();

    }

    @Override
    public List<FavListItem> sortByDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(FavListItem::getUpdate))
                .toList();
    }
}
