package com.formation.TPFormationFavManager.service.impl;

import com.formation.TPFormationFavManager.dto.FavDefinition;
import com.formation.TPFormationFavManager.dto.FavItem;
import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.exception.NotFoundException;
import com.formation.TPFormationFavManager.persistence.entity.Category;
import com.formation.TPFormationFavManager.persistence.entity.Favorite;
import com.formation.TPFormationFavManager.persistence.repository.CategoryRepository;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import com.formation.TPFormationFavManager.service.InterFavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FavService implements InterFavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CategoryRepository categoryRepository;

    public FavService(FavoriteRepository favoriteRepository, CategoryRepository categoryRepository) {
        this.favoriteRepository = favoriteRepository;
        this.categoryRepository = categoryRepository;
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
    public ResponseEntity<String> removeSelectedFav(List <Long> idsToDelete) {
        idsToDelete.forEach(id -> favoriteRepository.deleteById(id));
        return ResponseEntity.ok("Selected favorites deleted successfully");
    }

    @Override
    public FavItem addFavorite(Long idCategory, FavDefinition favDefinition) {
        Favorite addfavorite;
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundException("id category not found !"));

        addfavorite = new Favorite();
        addfavorite.setId(favDefinition.getId());
        addfavorite.setLink(favDefinition.getLink());
        addfavorite.setUpdate(favDefinition.getUpdate());
        addfavorite.setCategory(category);

        favoriteRepository.save(addfavorite);
        return new FavItem(addfavorite.getId(), addfavorite.getCategory(),
                addfavorite.getLink(), addfavorite.getUpdate());
    }
}
