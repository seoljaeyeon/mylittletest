package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.relation.AnswerHistory;

@Mapper
public interface AnswerHistoryMapper {

	@Select("SELECT c.*, MAX(nv.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
	        "JOIN answer a ON ah.answerNo = a.answerNo " +
	        "JOIN noteView nv ON nv.noteNo = ah.noteNo " +
	        "WHERE a.answerType = #{answerType} " +
	        "AND ah.userNo = #{userNo} " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getCategoryListByUserNoAndAnswerType(@Param("userNo") Integer userNo, @Param("answerType") Integer answerType);

	@Select("SELECT c.*, MAX(nv.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
	        "JOIN answer a ON ah.answerNo = a.answerNo " +
	        "JOIN noteView nv ON nv.noteNo = ah.noteNo " +
	        "WHERE a.answerType = #{answerType} " +
	        "AND ah.userNo = #{userNo} AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%')  " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getSimilarCategoryListByUserNoAndAnswerType(
			@Param("userNo") Integer userNo, 
			@Param("answerType") Integer answerType,
			@Param("searchInput") String searchInput);	
	
    @SelectProvider(type = SqlBuilder.class, method = "buildGetNoteListByUserNoAndAnswerType")
    List<Map<String, Object>> getNoteListByUserNoAndAnswerType(
            @Param("userNo") Integer userNo,
            @Param("answerType") Integer answerType,
            @Param("sort") String sort,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("searchType") Integer searchType,
            @Param("searchInput") String searchInput);

    class SqlBuilder {
        public String buildGetNoteListByUserNoAndAnswerType(Map<String, Object> params) {
            Integer userNo = (Integer) params.get("userNo");
            Integer answerType = (Integer) params.get("answerType");
            String sort = (String) params.get("sort");
            Integer limit = (Integer) params.get("limit");
            Integer offset = (Integer) params.get("offset");
            Integer searchType = (Integer) params.get("searchType");
            String searchInput = (String) params.get("searchInput");

            String orderByClause = getOrderByClause(sort);

            StringBuilder sql = new StringBuilder();
            sql.append("WITH BaseQuery AS (");
            sql.append("SELECT c.categoryTitle, n.noteTitle, n.createdAt AS noteCreatedAt, n.noteNo, ");
            sql.append("COUNT(DISTINCT CASE WHEN f.favoriteType = 1 THEN f.favoriteNo ELSE NULL END) AS favorite_count, ");
            sql.append("COUNT(DISTINCT r.replyNo) AS reply_count ");
            sql.append("FROM note n ");
            sql.append("JOIN noteUser nu ON nu.noteNo = n.noteNo ");
            sql.append("LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo ");
            sql.append("LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo ");
            sql.append("LEFT JOIN noteReply nr ON nr.noteNo = n.noteNo ");
            sql.append("JOIN noteCategory nc ON nc.noteNo = n.noteNo ");
            sql.append("JOIN category c ON c.categoryNo = nc.categoryNo ");
            sql.append("LEFT JOIN reply r ON r.replyNo = nr.replyNo ");
            sql.append("JOIN answerHistory ah ON ah.noteNo = n.noteNo AND ah.userNo = nu.userNo ");
            sql.append("JOIN answer a ON a.answerNo = ah.answerNo ");
            sql.append("WHERE nu.userNo = #{userNo} AND a.answerType = #{answerType} ");

            if (searchInput != null && !searchInput.isEmpty()) {
                switch (searchType) {
                    case 1:
                        sql.append("AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') ");
                        break;
                    case 2:
                        sql.append("AND n.noteTitle LIKE CONCAT('%', #{searchInput}, '%') ");
                        break;
                    case 3:
                        sql.append("AND (c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') OR n.noteTitle LIKE CONCAT('%', #{searchInput}, '%')) ");
                        break;
                }
            }

            sql.append("GROUP BY c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo ");
            sql.append("), OrderedNotes AS (");
            sql.append("SELECT categoryTitle, noteTitle, noteCreatedAt, noteNo, favorite_count, reply_count, ");
            sql.append("ROW_NUMBER() OVER (ORDER BY ").append(orderByClause).append(") AS row_num ");
            sql.append("FROM BaseQuery ");
            sql.append(") ");
            sql.append("SELECT categoryTitle, noteTitle, noteCreatedAt, noteNo, favorite_count, reply_count ");
            sql.append("FROM OrderedNotes ");
            sql.append("WHERE row_num BETWEEN #{offset} + 1 AND #{offset} + #{limit}");

            return sql.toString();
        }

        private String getOrderByClause(String sort) {
            switch (sort) {
                case "favorite":
                    return "favorite_count DESC";
                case "reply":
                    return "reply_count DESC";
                default:
                    return "noteCreatedAt DESC";
            }
        }
    }
    
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