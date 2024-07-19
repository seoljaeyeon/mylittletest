package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.Category;
import com.ksw.object.relation.NoteCategory;

@Mapper
public interface NoteCategoryMapper {
	
	@Select(""
	        + "SELECT c.categoryTitle AS categoryTitle, "
	        + "COUNT(nc.noteNo) AS noteCount " 
	        + "FROM category c " 
	        + "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " 
	        + "WHERE c.categoryTitle LIKE CONCAT('%', #{categoryTitle}, '%') " 
	        + "GROUP BY c.categoryTitle "
	        + "ORDER BY noteCount DESC")
	List<Map<String, Object>> findCategoryNoteCountsByTitle(@Param("categoryTitle") String categoryTitle);
	
	@Insert(""
			+ "INSERT INTO noteCategory "
			+ "(categoryNo, noteNo) "
			+ "VALUES "
			+ "(#{category.categoryNo}, #{note.noteNo})")
	void insert(NoteCategory noteCategory);
	
    @Select("SELECT noteNo FROM noteCategory "
    		+ "WHERE categoryNo = #{categoryNo}")
    List<Integer> findNoteNosByCategoryNo(int categoryNo);
    
    @Select("SELECT c.* FROM category c "
    		+ "join categoryView cv "
    		+ "on cv.categoryNo = c.categoryNo "
    		+ "where cv.noteNo = #{noteNo}")
    @Results({
    	@Result(property = "category.categoryNo", column = "categoryNo"),
    	@Result(property = "category.categoryTitle", column = "categoryTitle"),
    	@Result(property = "category.isActive", column = "isActive"),
    	@Result(property = "category.createdAt", column = "createdAt")
    	})
    NoteCategory getNoteCategorybynoteNo(Integer noteNo);
    
    @Select(""
    		+ "SELECT c.* "
    		+ "FROM category c JOIN noteCategory nc "
    		+ "ON c.categoryNo = nc.categoryNo "
    		+ "WHERE nc.noteNo = #{noteNo} ")
    Category getCategorybyNoteNo(Integer noteNo);
    
    @Select("(" +
            "SELECT noteNo FROM (" +
            "  SELECT nu.noteNo " +
            "  FROM noteUser nu " +
            "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
            "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE nu.userNo = #{userNo} " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "    AND nv.noteNo IS NULL " +
            "    AND IFNULL(f.favoriteType, 0) <> -1 " +
            "  UNION ALL " +
            "  SELECT nu.noteNo " +
            "  FROM noteUser nu " +
            "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
            "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE nu.userNo = #{userNo} " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "    AND nv.noteNo IS NULL " +
            ") AS subquery " +
            "ORDER BY RAND() " +
            "LIMIT 1" +
            ") " +
            "UNION " +
            "(" +
            "SELECT noteNo FROM (" +
            "  SELECT nu.noteNo " +
            "  FROM noteUser nu " +
            "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
            "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE nu.userNo = #{userNo} " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "    AND IFNULL(f.favoriteType, 0) <> -1 " +
            "  UNION ALL " +
            "  SELECT nu.noteNo " +
            "  FROM noteUser nu " +
            "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
            "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE nu.userNo = #{userNo} " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            ") AS subquery " +
            "ORDER BY RAND() " +
            "LIMIT 1" +
            ") " +
            "LIMIT 1")
    Integer getRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);
	
}
