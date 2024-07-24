package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ksw.object.entity.Category;
import com.ksw.object.relation.NoteCategory;

@Mapper
public interface NoteCategoryMapper {
	
	@Select("(" +
	        "SELECT noteNo FROM (" +
	        "  SELECT nc.noteNo " +
	        "  FROM noteCategory nc " +
	        "  JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "  LEFT JOIN noteView nv ON nc.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
	        "  LEFT JOIN favoriteNote fn ON nc.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
	        "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "  WHERE c.categoryTitle = #{categoryTitle} " +
	        "    AND nv.noteNo IS NULL " +
	        "    AND IFNULL(f.favoriteType, 0) <> -1 " +
	        "  GROUP BY nc.noteNo " +
	        "  UNION ALL " +
	        "  SELECT nc.noteNo " +
	        "  FROM noteCategory nc " +
	        "  JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "  LEFT JOIN noteView nv ON nc.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
	        "  LEFT JOIN favoriteNote fn ON nc.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
	        "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "  WHERE c.categoryTitle = #{categoryTitle} " +
	        "    AND nv.noteNo IS NULL " +
	        "  GROUP BY nc.noteNo " +
	        ") AS subquery " +
	        "ORDER BY RAND() " +
	        "LIMIT 1" +
	        ") " +
	        "UNION " +
	        "(" +
	        "SELECT noteNo FROM (" +
	        "  SELECT nc.noteNo " +
	        "  FROM noteCategory nc " +
	        "  JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "  LEFT JOIN noteView nv ON nc.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
	        "  LEFT JOIN favoriteNote fn ON nc.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
	        "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "  WHERE c.categoryTitle = #{categoryTitle} " +
	        "    AND IFNULL(f.favoriteType, 0) <> -1 " +
	        "  GROUP BY nc.noteNo " +
	        "  UNION ALL " +
	        "  SELECT nc.noteNo " +
	        "  FROM noteCategory nc " +
	        "  JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "  LEFT JOIN noteView nv ON nc.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
	        "  LEFT JOIN favoriteNote fn ON nc.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
	        "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "  WHERE c.categoryTitle = #{categoryTitle} " +
	        "  GROUP BY nc.noteNo " +
	        ") AS subquery " +
	        "ORDER BY RAND() " +
	        "LIMIT 1" +
	        ") " +
	        "LIMIT 1")
	Integer getAllQuestionRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);

	@SelectProvider(type = SqlBuilder.class, method = "buildGetNoteListByCategoryTitle")
    List<Map<String, Object>> getNoteListByCategoryTitle(
            @Param("categoryTitle") String categoryTitle,
            @Param("sort") String sort,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("searchType") Integer searchType,
            @Param("searchInput") String searchInput);
	class SqlBuilder {
	    public String buildGetNoteListByCategoryTitle(Map<String, Object> params) {
	        String categoryTitle = (String) params.get("categoryTitle");
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
	        sql.append("COUNT(DISTINCT nr.replyNo) AS reply_count ");
	        sql.append("FROM note n ");
	        sql.append("JOIN noteCategory nc ON nc.noteNo = n.noteNo ");
	        sql.append("JOIN category c ON c.categoryNo = nc.categoryNo ");
	        sql.append("LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo ");
	        sql.append("LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo ");
	        sql.append("LEFT JOIN noteReply nr ON nr.noteNo = n.noteNo ");

	        if (categoryTitle != null && !categoryTitle.isEmpty()) {
	            sql.append("WHERE c.categoryTitle = #{categoryTitle} ");
	        }

	        if (searchInput != null && !searchInput.isEmpty()) {
	            if (categoryTitle != null && !categoryTitle.isEmpty()) {
	                sql.append("AND ");
	            } else {
	                sql.append("WHERE ");
	            }

	            switch (searchType) {
	                case 1:
	                    sql.append("c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') ");
	                    break;
	                case 2:
	                    sql.append("n.noteTitle LIKE CONCAT('%', #{searchInput}, '%') ");
	                    break;
	                case 3:
	                    sql.append("(c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') OR n.noteTitle LIKE CONCAT('%', #{searchInput}, '%')) ");
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
            "  GROUP BY nu.noteNo " +
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
            "  GROUP BY nu.noteNo " +
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
            "  GROUP BY nu.noteNo " +
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
            "  GROUP BY nu.noteNo " +
            ") AS subquery " +
            "ORDER BY RAND() " +
            "LIMIT 1" +
            ") " +
            "LIMIT 1")
    Integer getRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);
	//틀린문제
    @Select("SELECT noteNo FROM (" +
            "  SELECT nc.noteNo " +
            "  FROM noteCategory nc " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
            "  JOIN answer a ON ah.answerNo = a.answerNo " +
            "  LEFT JOIN favoriteNote fn ON ah.noteNo = fn.noteNo " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE ah.userNo = #{userNo} " +
            "    AND IFNULL(f.favoriteType, 0) <> -1 " +
            "    AND a.answerType = 1 " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "  GROUP BY nc.noteNo " +
            "  UNION ALL " +
            "  SELECT nc.noteNo " +
            "  FROM noteCategory nc " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
            "  JOIN answer a ON ah.answerNo = a.answerNo " +
            "  LEFT JOIN favoriteNote fn ON ah.noteNo = fn.noteNo " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE ah.userNo = #{userNo} " +
            "    AND a.answerType = 1 " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "  GROUP BY nc.noteNo " +
            ") AS subquery " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Integer getCorrectRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);
    // 맞춘 문제
    @Select("SELECT noteNo FROM (" +
            "  SELECT nc.noteNo " +
            "  FROM noteCategory nc " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
            "  JOIN answer a ON ah.answerNo = a.answerNo " +
            "  LEFT JOIN favoriteNote fn ON ah.noteNo = fn.noteNo " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE ah.userNo = #{userNo} " +
            "    AND IFNULL(f.favoriteType, 0) <> -1 " +
            "    AND a.answerType = 2 " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "  GROUP BY nc.noteNo " +
            "  UNION ALL " +
            "  SELECT nc.noteNo " +
            "  FROM noteCategory nc " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  JOIN answerHistory ah ON ah.noteNo = nc.noteNo " +
            "  JOIN answer a ON ah.answerNo = a.answerNo " +
            "  LEFT JOIN favoriteNote fn ON ah.noteNo = fn.noteNo " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE ah.userNo = #{userNo} " +
            "    AND a.answerType = 2 " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "  GROUP BY nc.noteNo " +
            ") AS subquery " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Integer getReviewRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);
    //오늘 본 문제
    @Select(
    	    "SELECT noteNo FROM (" +
    	    "  SELECT nu.noteNo " +
    	    "  FROM noteUser nu " +
    	    "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
    	    "  JOIN category c ON nc.categoryNo = c.categoryNo " +
    	    "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
    	    "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
    	    "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
    	    "  WHERE nu.userNo = #{userNo} " +
    	    "  AND c.categoryTitle = #{categoryTitle} " +
    	    "  AND IFNULL(f.favoriteType, 0) <> -1 " +
    	    "  AND DATE(nv.createdAt) = CURRENT_DATE " +
    	    "  GROUP BY nu.noteNo " +
    	    "  UNION ALL " +
    	    "  SELECT nu.noteNo " +
    	    "  FROM noteUser nu " +
    	    "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
    	    "  JOIN category c ON nc.categoryNo = c.categoryNo " +
    	    "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
    	    "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
    	    "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
    	    "  WHERE nu.userNo = #{userNo} " +
    	    "  AND c.categoryTitle = #{categoryTitle} " +
    	    "  AND DATE(nv.createdAt) = CURRENT_DATE " +
    	    "  GROUP BY nu.noteNo " +
    	    ") AS subquery " +
    	    "ORDER BY RAND() " +
    	    "LIMIT 1")
    Integer getTodayQuestionRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
            @Param("userNo") Integer userNo);

    // 북마크문제
    @Select(
            "SELECT nu.noteNo " +
            "  FROM noteUser nu " +
            "  JOIN noteCategory nc ON nu.noteNo = nc.noteNo " +
            "  JOIN category c ON nc.categoryNo = c.categoryNo " +
            "  LEFT JOIN noteView nv ON nu.noteNo = nv.noteNo AND nv.userNo = #{userNo} " +
            "  LEFT JOIN favoriteNote fn ON nu.noteNo = fn.noteNo AND fn.userNo = #{userNo} " +
            "  LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
            "  WHERE nu.userNo = #{userNo} " +
            "    AND c.categoryTitle = #{categoryTitle} " +
            "    AND (f.favoriteType = 2 OR f.favoriteType = 1) " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Integer getBookmarkQuestionRandomNoteNo(@Param("categoryTitle") String categoryTitle, 
                            @Param("userNo") Integer userNo);
}
