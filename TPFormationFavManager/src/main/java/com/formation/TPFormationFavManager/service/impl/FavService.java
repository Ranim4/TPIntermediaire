package com.formation.TPFormationFavManager.service.impl;

import com.formation.TPFormationFavManager.dto.CategoryListItem;
import com.formation.TPFormationFavManager.dto.FavDefinition;
import com.formation.TPFormationFavManager.dto.FavItem;
import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.exception.NotFoundException;
import com.formation.TPFormationFavManager.persistence.entity.Category;
import com.formation.TPFormationFavManager.persistence.entity.Favorite;
import com.formation.TPFormationFavManager.persistence.repository.CategoryRepository;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import com.formation.TPFormationFavManager.service.InterFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FavService implements InterFavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    //list all favorites
    @Override
    public List<FavListItem> findAll() {
        return favoriteRepository.findAll()
                .stream() //get a stream of list elements, on which we can apply various operations.
                .map(f -> new FavListItem(f.getId(), f.getCategory(), f.getLink(), f.getUpdate())) // transform each item in the stream (favorite) into FavListItem
                .toList(); //to collect the items from the resulting stream and store them in a new list.
    }

    //sort the favorites list according to category
    @Override
    public List<FavListItem> sortByCategory() {
        return findAll().stream()
                .sorted((item1, item2)->item1.getCategory().getLabel().
                        compareTo(item2.getCategory().getLabel())) //sort the items in the stream according to category Label
                .toList();

    }

    //sort the favorites list according to last update
    @Override
    public List<FavListItem> sortByDate() {
        return findAll().stream()
                .sorted(Comparator.comparing(FavListItem::getUpdate)) //sort the items in the stream according to the last update
                .toList();
    }

    //filter the favorites list by category label
    @Override
    public List<FavListItem> filterByCategory(String category) {
        return findAll().stream()
                .filter(fav -> fav.getCategory().getLabel().equalsIgnoreCase(category)) //to filter the elements of the stream. Only items that satisfy the condition (CategoryLabel = given category) will be included in the resulting stream.
                .toList();
    }

    //Delete the selected favorites from the list
    @Override
    public ResponseEntity<String> removeSelectedFav(List <Long> idsToDelete) {
        idsToDelete.forEach(id -> favoriteRepository.deleteById(id)); //delete the item if its id is included in the given list (idsToDelete)
        return ResponseEntity.ok("Selected favorites deleted successfully");
    }

    // add a favorite to the current list
    @Override
    public FavItem addFavorite(Long idCategory, FavDefinition favDefinition) {
        Favorite addfavorite;
        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundException("id category not found !")); //check if given category id exists or not

        addfavorite = new Favorite();
        addfavorite.setId(favDefinition.getId());
        addfavorite.setLink(favDefinition.getLink());
        addfavorite.setUpdate(favDefinition.getUpdate());
        addfavorite.setCategory(category);

        favoriteRepository.save(addfavorite); //add the new favorite to the favorites list
        return new FavItem(addfavorite.getId(), addfavorite.getCategory(),
                addfavorite.getLink(), addfavorite.getUpdate()); //convert favorite -> FavItem
    }

    //Calculate the total number of existing favorites
    @Override
    public Long totalNumberFav() {
        return findAll().stream().count();
    }

    //Calculate the number of selected favorites
    @Override
    public Long slectedNumberFav(List<Long> idsSelectedFav) {
        return idsSelectedFav.stream().count();
    }

    @Override
    public List<CategoryListItem> findAllCategory() {
        return categoryRepository.findAll()
                .stream() //get a stream of list elements, on which we can apply various operations.
                .map(c -> new CategoryListItem(c.getId(), c.getLabel())) // transform each item in the stream (category) into CategoryListItem
                .toList(); //to collect the items from the resulting stream and store them in a new list.
    }
}
