package com.ksw.dao.forObject.relation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface NoteViewMapper {
	
	
	@Select("SELECT " +
	        "DATE(createdAt) AS viewDate, " +
	        "COUNT(*) AS viewCount " +
	        "FROM noteView " +
	        "WHERE userNo = #{userNo} " +
	        "AND createdAt >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
	        "GROUP BY DATE(createdAt) " +
	        "ORDER BY viewDate DESC")
	List<Map<String, Object>> getRecentViewCounts(@Param("userNo") Integer userNo);
	
    @Select("SELECT c.*, MAX(n.createdAt) AS createdAt, COUNT(CASE WHEN nc.categoryNo = c.categoryNo THEN 1 END) AS noteCount " +
            "FROM category c " +
            "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
            "JOIN note n ON nc.noteNo = n.noteNo " +
            "WHERE c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') " +
            "GROUP BY c.categoryNo " +
            "ORDER BY createdAt DESC")
    List<Map<String, Object>> getSimilarCategoryListOrderedByNoteView(@Param("searchInput") String searchInput);
    
    @Select("SELECT c.*, MAX(n.createdAt) AS createdAt, COUNT(CASE WHEN nc.categoryNo = c.categoryNo THEN 1 END) AS noteCount " +
            "FROM category c " +
            "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
            "JOIN note n ON nc.noteNo = n.noteNo " +
            "GROUP BY c.categoryNo " +
            "ORDER BY createdAt DESC")
    List<Map<String, Object>> getCategoryListOrderedByNoteView();
	//allcategory
    
	@Select("SELECT c.*, MAX(nv.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN noteView nv ON nv.noteNo = nc.noteNo " +
	        "WHERE nv.userNo = #{userNo} AND DATE(nv.createdAt) = CURDATE() " +
	        " AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getTodaySimilarCategoryListByUserNo(
			@Param("userNo") Integer userNo,
			@Param("searchInput") String searchInput);
	
	@Select("SELECT c.*, MAX(nv.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN noteView nv ON nv.noteNo = nc.noteNo " +
	        "WHERE nv.userNo = #{userNo} AND DATE(nv.createdAt) = CURDATE() " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getTodayCategoryListByUserNo(@Param("userNo") Integer userNo);
	
	@SelectProvider(type = SqlBuilder.class, method = "buildGetNoteListByUserNo")
    List<Map<String, Object>> getNoteListByUserNo(
            @Param("userNo") Integer userNo,
            @Param("sort") String sort,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("searchType") Integer searchType,
            @Param("searchInput") String searchInput);

	class SqlBuilder {
	    public String buildGetNoteListByUserNo(Map<String, Object> params) {
	        Integer userNo = (Integer) params.get("userNo");
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
	        sql.append("JOIN noteCategory nc ON nc.noteNo = n.noteNo ");
	        sql.append("JOIN category c ON c.categoryNo = nc.categoryNo ");
	        sql.append("LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo ");
	        sql.append("LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo ");
	        sql.append("LEFT JOIN noteReply nr ON nr.noteNo = n.noteNo ");
	        sql.append("LEFT JOIN reply r ON r.replyNo = nr.replyNo ");
	        sql.append("JOIN noteView nv ON nv.noteNo = n.noteNo ");
	        sql.append("JOIN view v ON v.viewNo = nv.viewNo ");
	        sql.append("WHERE nv.userNo = #{userNo} ");

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