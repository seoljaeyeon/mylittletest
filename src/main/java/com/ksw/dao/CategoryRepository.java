package com.ksw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
