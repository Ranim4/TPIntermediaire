package com.formation.TPFormationFavManager.persistence.repository;

import com.formation.TPFormationFavManager.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
