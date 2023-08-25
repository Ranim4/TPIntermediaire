package com.formation.TPFormationFavManager.controller;

import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.service.impl.FavService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
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
}
