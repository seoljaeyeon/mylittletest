package com.ksw.dao.forObject.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
