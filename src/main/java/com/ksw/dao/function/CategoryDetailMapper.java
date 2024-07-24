package com.ksw.dao.function;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryDetailMapper {
	
	// 해당 카테고리로 작성된 note의 수를 Map형태로 반환하고 목록을 list로 반환
    @Select("SELECT c.*, COUNT(nc.noteNo) AS noteCount " +
            "FROM category c " +
            "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
            "WHERE c.categoryNo = #{categoryNo} " +
            "GROUP BY c.categoryNo " +
            "ORDER BY noteCount DESC " +
            "LIMIT #{limit} OFFSET #{offset}")
    @Results({
        @Result(property = "categoryNo", column = "categoryNo"),
        @Result(property = "categoryTitle", column = "categoryTitle"),
        @Result(property = "createdAt", column = "createdAt"),
        @Result(property = "isActive", column = "isActive")
    })
    List<Map<String, Object>> getCategoryWithNoteCount(@Param("categoryNo") Integer categoryNo,
                                                       @Param("limit") Integer limit,
                                                       @Param("offset") Integer offset);

    @Select("SELECT nc.categoryNo, " +
            "CAST(SUM(CASE WHEN a.answerType = 2 THEN 1 ELSE 0 END) AS DECIMAL) / " +
            "CAST(COUNT(CASE WHEN ah.answerNo IS NOT NULL THEN 1 END) AS DECIMAL) AS correctRatio " +
            "FROM noteCategory nc " +
            "JOIN answerHistory ah ON nc.noteNo = ah.noteNo " +
            "JOIN answer a ON ah.answerNo = a.answerNo " +
            "WHERE nc.categoryNo = #{categoryNo} AND ah.userNo = #{userNo} " +
            "GROUP BY nc.categoryNo")
    Map<String, Object> getCorrectRatio(@Param("categoryNo") Integer categoryNo,
                                        @Param("userNo") Integer userNo);
    
    @Select("SELECT nc.categoryNo, COUNT(DISTINCT nu.userNo) AS authorCount " +
            "FROM noteCategory nc " +
            "JOIN noteUser nu ON nc.noteNo = nu.noteNo " +
            "WHERE nc.categoryNo = #{categoryNo} " +
            "GROUP BY nc.categoryNo")
    Map<String, Object> getAuthorCount(@Param("categoryNo") Integer categoryNo);

    @Select("SELECT nc.categoryNo, COUNT(fn.favoriteNo) AS favoriteCount " +
            "FROM noteCategory nc " +
            "JOIN favoriteNote fn ON nc.noteNo = fn.noteNo " +
            "JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "WHERE nc.categoryNo = #{categoryNo} AND f.favoriteType = 1 " +
            "GROUP BY nc.categoryNo")
    Map<String, Object> getFavoriteCount(@Param("categoryNo") Integer categoryNo);
}