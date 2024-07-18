package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.NoteView;

public interface NoteViewMapper {

	
//		잠시 비활성
//    @Insert(""
//            + "INSERT INTO noteUser (noteNo, viewNo, userNo, createdAt) "
//            + "SELECT #{noteNo}, #{viewNo}, #{userNo}, #{createdAt} FROM DUAL "
//            + "WHERE NOT EXISTS ("
//            + "  SELECT 1 FROM ("
//            + "    SELECT 1 FROM noteUser nu "
//            + "    WHERE nu.noteNo = #{noteNo} "
//            + "    AND nu.viewNo = #{viewNo} "
//            + "    AND nu.userNo = #{userNo} "
//            + "    ORDER BY nu.createdAt DESC "
//            + "    LIMIT 1"
//            + "  ) As subquery "
//            + "  WHERE TIMESTAMPDIFF(MINUTE, subquery.createdAt, #{createdAt}) <= 5)"
//            + "")
//	void insert(@Param("viewNo") Integer viewNo,
//				@Param("noteNo") Integer noteNo,
//				@Param("userNo") Integer userNo, 
//				@Param("createdAt") Timestamp createdAt);
	
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