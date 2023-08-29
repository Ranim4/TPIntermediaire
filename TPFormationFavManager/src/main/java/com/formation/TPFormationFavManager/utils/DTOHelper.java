package com.formation.TPFormationFavManager.utils;

import com.formation.TPFormationFavManager.dto.CategoryItem;
import com.formation.TPFormationFavManager.dto.CategoryListItem;
import com.formation.TPFormationFavManager.dto.FavItem;
import com.formation.TPFormationFavManager.dto.FavListItem;
import com.formation.TPFormationFavManager.persistence.entity.Category;
import com.formation.TPFormationFavManager.persistence.entity.Favorite;
import com.formation.TPFormationFavManager.persistence.repository.CategoryRepository;
import com.formation.TPFormationFavManager.persistence.repository.FavoriteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOHelper {

    private ModelMapper mapper = new ModelMapper();

    public FavListItem toFavListItem (Favorite entity){
        FavListItem dto = mapper.map(entity, FavListItem.class);
        return dto;
    }

    public FavItem toFavItem (Favorite entity){
        FavItem dto = mapper.map(entity, FavItem.class);
        return dto;
    }

    public CategoryItem toCategoryItem (Category entity){
        CategoryItem dto = mapper.map(entity, CategoryItem.class);
        return dto;
    }

    public CategoryListItem toCategoryListItem (Category entity){
        CategoryListItem dto = mapper.map(entity, CategoryListItem.class);
        return dto;
    }
}
