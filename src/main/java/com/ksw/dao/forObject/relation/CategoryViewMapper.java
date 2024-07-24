package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.CategoryView;

public interface CategoryViewMapper {
	
	@Select("SELECT c.categoryTitle, MAX(cv.createdAt) AS createdAt "
			+ "FROM category c JOIN categoryView cv ON c.categoryNo = cv.categoryNo "
			+ "JOIN favoriteNote fn ON fn.userNo = cv.userNo "
			+ "JOIN favorite f ON f.favoriteNo = fn.favoriteNo "
			+ "WHERE (f.favoriteType = #{favoriteType} OR f.favoriteType = 2) AND fn.userNo = #{userNo} AND "
			+ "DATE(cv.createdAt) = CURDATE() "
			+ "GROUP BY c.categoryTitle "
			+ "ORDER BY createdAt DESC")
	List<Map<String, Object>> getTodayHistoryByUserNoAndFavoriteType(
			@Param("userNo") Integer userNo, 
			@Param("favoriteType") Integer favoriteType);
	
	@Select("SELECT c.categoryTitle, MAX(cv.createdAt) AS createdAt "
			+ "FROM category c Join categoryView cv ON c.categoryNo = cv.categoryNo "
			+ "WHERE cv.userNo = #{userNo} ANd DATE(cv.createdAt) = CURDATE() "
			+ "GROUP BY c.categoryTitle "
			+ "ORDER BY createdAt DESC ")
	List<Map<String, Object>> getTodayHistoryByUserNo(
			@Param("userNo") Integer userNo);
	
	@Select("SELECT c.categoryTitle, MAX(cv.createdAt) AS createdAt "
	        + "FROM category c JOIN categoryView cv ON c.categoryNo = cv.categoryNo "
	        + "JOIN noteCategory nc ON nc.categoryNo = c.categoryNo "
	        + "JOIN answerHistory ah ON ah.noteNo = nc.noteNo "
	        + "JOIN answer a ON ah.answerNo = a.answerNo "
	        + "WHERE a.answerType = #{answerType} AND DATE(cv.createdAt) = CURDATE() "
	        + "AND ah.userNo = #{userNo} "
	        + "GROUP BY c.categoryTitle "
	        + "ORDER BY createdAt DESC")
	List<Map<String,Object>> getTodayHistoryByUserNoAndAnswerType(
			@Param("userNo") Integer userNo, 
			@Param("answerType") Integer answerType);
	
	@Select("SELECT c.categoryTitle, MAX(cv.createdAt) AS createdAt "
	        + "FROM category c JOIN categoryView cv ON c.categoryNo = cv.categoryNo "
	        + "JOIN noteCategory nc ON nc.categoryNo = c.categoryNo "
	        + "JOIN noteUser nu ON nu.noteNo = nc.noteNo "
	        + "WHERE cv.userNo = #{userNo} AND DATE(cv.createdAt) = CURDATE() AND nu.userNo = #{userNo} "
	        + "GROUP BY c.categoryTitle "
	        + "ORDER BY createdAt DESC")
	List<Map<String,Object>> getTodayHistoryByUserNoOfMyTest(
	        @Param("userNo") Integer userNo);
	
	@Insert("INSERT INTO categoryView (viewNo, categoryNo, userNo) "
			+ "VALUES (#{viewNo}, #{categoryNo}, #{userNo})")
	void insert(
			@Param("viewNo") Integer viewNo,
			@Param("categoryNo") Integer categoryNo,
			@Param("userNo") Integer userNo
			);
	
	@Select(""
			+ "    SELECT COUNT(*) "
			+ "    FROM categoryView "
			+ "    WHERE categoryNo = #{categoryNo} "
			+ "    AND userNo = #{userNo} "
			+ "    AND TIMESTAMPDIFF(MINUTE, createdAt, NOW()) < 5")
	Integer checkRecentViewHistory(			
			@Param("categoryNo") Integer categoryNo,
			@Param("userNo") Integer userNo);

    @Insert("INSERT INTO categoryView (categoryNo, viewNo) VALUES (#{categoryNo}, #{viewNo})")
    void insertCategoryView(CategoryView categoryView);

    @Select("SELECT * FROM categoryView WHERE categoryNo = #{categoryNo}")
    CategoryView getCategoryViewByCategoryNo(@Param("categoryNo") int categoryNo);

    @Select("SELECT * FROM categoryView")
    List<CategoryView> getAllCategoryViews();
}