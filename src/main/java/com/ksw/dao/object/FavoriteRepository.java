package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{

}
