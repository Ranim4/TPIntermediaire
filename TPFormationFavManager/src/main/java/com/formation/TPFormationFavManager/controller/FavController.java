package com.formation.TPFormationFavManager.controller;

import com.formation.TPFormationFavManager.dto.FavDefinition;
import com.formation.TPFormationFavManager.dto.FavItem;
import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.service.impl.FavService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorites")
public class FavController {

    private final FavService favService;

    public FavController(FavService favService) {
        this.favService = favService;
    }

    @GetMapping
    List<FavListItem> findAll(){
        return favService.findAll();
    }

    @GetMapping(path = "/sortedByCat")
    List<FavListItem> sortByCategory(){
        return favService.sortByCategory();
    }

    @GetMapping(path = "/sortedByDate")
    List<FavListItem> sortByDate(){
        return favService.sortByDate();
    }

    @GetMapping(path = "/{category}")
    public List<FavListItem> filterByCategory(@PathVariable String category) {
        return favService.filterByCategory(category);
    }

    @DeleteMapping(path = "/{idsToDelete}")
    public ResponseEntity<String> removeSelectedFav(@PathVariable List<Long> idsToDelete){
        favService.removeSelectedFav(idsToDelete);
        return ResponseEntity.ok("Selected favorites deleted successfully");
    }

    @PostMapping(path = "/{idCategory}/favorite")
    public FavItem addFavorite(@PathVariable Long idCategory, @RequestBody FavDefinition favDefinition){
        return favService.addFavorite(idCategory, favDefinition);
    }

    // Returns the total number of saved favorites
    @GetMapping(path = "/totalNumber")
    public Long totalNumberFav() {
        return favService.totalNumberFav();
    }
}
