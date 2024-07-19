package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.NoteView;

public interface NoteViewMapper {

	
	
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