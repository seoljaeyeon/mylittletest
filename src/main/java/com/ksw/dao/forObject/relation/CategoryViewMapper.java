package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.CategoryView;

public interface CategoryViewMapper {

    @Insert("INSERT INTO categoryView (categoryNo, viewNo) VALUES (#{categoryNo}, #{viewNo})")
    void insertCategoryView(CategoryView categoryView);

    @Select("SELECT * FROM categoryView WHERE categoryNo = #{categoryNo}")
    CategoryView getCategoryViewByCategoryNo(@Param("categoryNo") int categoryNo);

    @Select("SELECT * FROM categoryView")
    List<CategoryView> getAllCategoryViews();
}