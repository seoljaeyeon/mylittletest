package com.ksw.dao.forObject.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
    boolean existsByCategoryTitle(String categoryTitle);
    
    Category findByCategoryTitle(String categoryTitle);

    // jpa에 커스텀 쿼리 사용하는 
//    @Query("SELECT c.categoryTitle AS categoryTitle, COUNT(nc.noteNo) AS noteCount " +
//            "FROM Category c " +
//            "JOIN NoteCategory nc ON c.categoryNo = nc.categoryNo " +
//            "WHERE c.categoryTitle LIKE %:categoryTitle% " +
//            "GROUP BY c.categoryTitle")
//    List<Object[]> findCategoryNoteCountsByTitle(@Param("categoryTitle") String categoryTitle);    
}
