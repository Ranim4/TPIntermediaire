package com.formation.TPFormationFavManager.service.impl;

import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.exception.NotFoundException;
import com.formation.TPFormationFavManager.persistence.entity.Category;
import com.formation.TPFormationFavManager.persistence.entity.Favorite;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import com.formation.TPFormationFavManager.service.InterFavoriteService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
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

    @Override
    public List<FavListItem> filterByCategory( String category) {
        return findAll().stream()
                .filter(fav -> fav.getCategory().getLabel().equals(category))
                .toList();
    }

    @Override
    @Transactional
    public ResponseEntity<String> removeSelectedFav(List <Long> idsToDelete) {
        idsToDelete.forEach(id -> favoriteRepository.deleteById(id));
        return ResponseEntity.ok("Selected favorites deleted successfully");
    }
}
