package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.relation.AnswerHistory;

@Mapper
public interface AnswerHistoryMapper {

	@Select("SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
	        "JOIN answer a ON ah.answerNo = a.answerNo " +
	        "JOIN noteView nv ON nv.noteNo = ah.noteNo " +
	        "WHERE a.answerType = #{answerType} " +
	        "AND ah.userNo = #{userNo} " +
	        "GROUP BY c.categoryNo " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getCategoryListByUserNoAndAnswerType(@Param("userNo") Integer userNo, @Param("answerType") Integer answerType);

	@Select("SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
	        "JOIN answer a ON ah.answerNo = a.answerNo " +
	        "JOIN noteView nv ON nv.noteNo = ah.noteNo " +
	        "WHERE a.answerType = #{answerType} " +
	        "AND ah.userNo = #{userNo} AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%')  " +
	        "GROUP BY c.categoryNo " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getSimilarCategoryListByUserNoAndAnswerType(
			@Param("userNo") Integer userNo, 
			@Param("answerType") Integer answerType,
			@Param("searchInput") String searchInput);
	
	
	@Select("SELECT c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo, "
	        + "COUNT(CASE WHEN f.favoriteType = 2 THEN 1 ELSE NULL END) AS favorite_count, "
	        + "COUNT(r.replyNo) AS reply_count "
	        + "FROM note n "
	        + "JOIN noteUser nu ON nu.noteNo = n.noteNo "
	        + "LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo " // 좋아요가 없는 노트도 포함
	        + "LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo "
	        + "LEFT JOIN noteReply nr ON nr.noteNo = n.noteNo " // 댓글이 없는 노트도 포함
	        + "JOIN noteCategory nc ON nc.noteNo = n.noteNo "
	        + "JOIN category c ON c.categoryNo = nc.categoryNo "
	        + "LEFT JOIN reply r ON r.replyNo = nr.replyNo " // 댓글이 없는 노트도 포함
	        + "JOIN answerHistory ah ON ah.noteNo = n.noteNo AND ah.userNo = nu.userNo " // answerHistory와 조인 조건 수정
	        + "JOIN answer a ON a.answerNo = ah.answerNo "
	        + "WHERE nu.userNo = #{userNo} AND a.answerType = #{answerType} "
	        + "GROUP BY c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo "
	        + "ORDER BY n.createdAt DESC")
	List<Map<String, Object>> getNoteListByUserNoAndAnswerType(@Param("userNo") Integer userNo, @Param("answerType") Integer answerType);

	@Update("UPDATE answerHistory "
	        + "SET answerNo = #{answerNo}, createdAt = NOW() "
	        + "WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
	Integer updateHistory(@Param("noteNo") Integer noteNo, @Param("answerNo") Integer answerNo, @Param("userNo") Integer userNo);

	@Insert("INSERT INTO answerHistory "
			+ "(noteNo, answerNo, userNo, createdAt) "
			+ "VALUES (#{noteNo}, #{answerNo}, #{userNo}, #{createdAt})")
	Integer insertHistory(@Param("noteNo") Integer noteNo, @Param("answerNo") Integer answerNo, @Param("userNo") Integer userNo, @Param("createdAt") Timestamp createdAt);
	
    @Select("SELECT a.answerType "
    		+ "FROM answerHistory ah "
    		+ "JOIN answer a ON ah.answerNo = a.answerNo "
    		+ "WHERE ah.noteNo = #{noteNo} "
    		+ "AND ah.userNo = #{userNo} "
    		+ "ORDER BY ah.createdAt DESC LIMIT 1")
    Integer findAnswerByNoteNoAndUserNo(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);
    
    @Select("SELECT answerNo "
    		+ "FROM answerHistory "
    		+ "WHERE noteNo = #{noteNo} AND userNo = #{userNo} "
    		+ "LIMIT 1")
    Integer getAnswerNoByNoteNoAndUserNo(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);

    @Select("SELECT * FROM answerHistory WHERE noteNo = #{noteNo}")
    List<AnswerHistory> findByNoteNo(Integer noteNo);

    @Select("SELECT * FROM answerHistory")
    List<AnswerHistory> findAll();

    @Update("UPDATE answerHistory SET answerNo = #{answerNo}, createdAt = #{createdAt}, updatedAt = #{updatedAt} WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void update(AnswerHistory answerHistory);

    @Delete("DELETE FROM answerHistory WHERE noteNo = #{noteNo} AND userNo = #{userNo}")
    void delete(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);
}