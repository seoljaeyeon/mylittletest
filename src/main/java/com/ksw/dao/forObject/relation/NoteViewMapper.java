package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface NoteViewMapper {
	
	
	@Select("WITH temp_table AS (" +
	        "    SELECT c.categoryNo, COUNT(nv.viewNo) AS viewcount " +
	        "    FROM category c " +
	        "    JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "    LEFT JOIN noteView nv ON nv.noteNo = nc.noteNo " +
	        "    GROUP BY c.categoryNo " +
	        ") " +
	        "SELECT categoryNo " +
	        "FROM temp_table " +
	        "ORDER BY viewcount DESC")
	List<Integer> getCategoryListOrderedByNoteView();

	
	@Select("WITH temp_table AS (" +
			"    SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt "
			+ "FROM category c " 
			+ "JOIN noteCategory nc ON nc.categoryNo = c.categoryNo "
			+ "JOIN noteView nv ON nv.noteNo = nc.noteNo "
			+ "WHERE nv.userNo = #{userNo} AND DATE(nv.createdAt) = CURDATE()) " +
            "SELECT categoryNo " +
            "FROM temp_table " +
            "ORDER BY createdAt DESC")
	List<Integer> getTodayCategoryListByUserNo(@Param("userNo") Integer userNo);
	
	@Select("SELECT c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo, "
	        + "COUNT(CASE WHEN f.favoriteType = 2 THEN 1 ELSE NULL END) AS favorite_count, "
	        + "COUNT(r.replyNo) AS reply_count "
	        + "FROM note n "
	        + "JOIN noteCategory nc ON nc.noteNo = n.noteNo "
	        + "JOIN category c ON c.categoryNo = nc.categoryNo "
	        + "LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo "
	        + "LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo "
	        + "LEFT JOIN reply r ON r.noteNo = n.noteNo "
	        + "JOIN noteView nv ON nv.noteNo = n.noteNo "
	        + "JOIN view v ON v.viewNo = nv.viewNo "
	        + "WHERE nv.userNo = #{userNo} "
	        + "GROUP BY c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo "
	        + "ORDER BY n.createdAt DESC")
	List<Map<String, Object>> getNoteListByUserNo(@Param("userNo") Integer userNo);

	
	@Select(""
			+ "SELECT n.noteNo, nv.createdAt FROM note n JOIN noteCategory nc ON n.noteNo = nc.noteNo "
			+ "JOIN noteView nv ON n.noteNo = nv.noteNo "
			+ "WHERE nc.categoryNo = #{categoryNo} AND nv.userNo = #{userNo} "
			+ "ORDER BY createdAt DESC "
			+ "LIMIT 1" 
			)
	Integer getPreviousNoteNo(@Param("categoryNo") Integer categoryNo, @Param("userNo") Integer userNo);
	
	@Insert(""
			+ "INSERT INTO noteView (noteNo, viewNo, userNo, createdAt) "
			+ "VALUES"
			+ "(#{noteNo}, #{viewNo}, #{userNo}, #{createdAt})")
	void insert(@Param("viewNo") Integer viewNo,
			@Param("noteNo") Integer noteNo,
			@Param("userNo") Integer userNo, 
			@Param("createdAt") Timestamp createdAt);

	@Select(""
			+ "    SELECT COUNT(*) "
			+ "    FROM noteView "
			+ "    WHERE noteNo = #{noteNo} "
			+ "    AND userNo = #{userNo} "
			+ "    AND TIMESTAMPDIFF(MINUTE, createdAt, NOW()) < 5")
	Integer checkRecentViewHistory(			
			@Param("noteNo") Integer noteNo,
			@Param("userNo") Integer userNo);
	
    
	@Select(""
	        + "SELECT COUNT(DISTINCT nv.noteNo) FROM "
	        + "noteView nv JOIN noteCategory nc ON nv.noteNo = nc.noteNo "
	        + "WHERE nv.userNo = #{userNo} "
	        + "AND nc.categoryNo = #{categoryNo} "
	        + "AND DATE(nv.createdAt) = CURDATE()") // 날짜 조건 - 오늘 
	Integer getTodayHistory(@Param("categoryNo") Integer categoryNo,
	                        @Param("userNo") Integer userNo);
}