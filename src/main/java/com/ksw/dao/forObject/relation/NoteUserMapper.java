package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.ksw.dao.forObject.relation.AnswerHistoryMapper.SqlBuilder;
import com.ksw.object.entity.User;
import com.ksw.object.relation.NoteUser;

@Mapper
public interface NoteUserMapper {
	
	@Select("SELECT c.*, MAX(n.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN noteUser nu ON nu.noteNo = nc.noteNo " +
	        "JOIN note n ON nc.noteNo = n.noteNo " +
	        "WHERE nu.userNo = #{userNo} " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getCategoryListByUserNo(@Param("userNo") Integer userNo);

	@Select("SELECT c.*, MAX(n.createdAt) AS createdAt, COUNT(nc.categoryNo) AS noteCount " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN noteUser nu ON nu.noteNo = nc.noteNo " +
	        "JOIN note n ON nc.noteNo = n.noteNo " +
			"WHERE nu.userNo = #{userNo} AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') " +
	        "GROUP BY c.categoryNo, c.categoryTitle, c.createdAt, c.isActive " +
	        "ORDER BY createdAt DESC")
	List<Map<String,Object>> getSimilarCategoryListByUserNo(@Param("userNo") Integer userNo, @Param("searchInput") String searchInput);
	
	@Select("SELECT c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo, "
	        + "COUNT(CASE WHEN f.favoriteType = 2 THEN 1 ELSE NULL END) AS favorite_count, "
	        + "COUNT(r.replyNo) AS reply_count "
	        + "FROM note n "
	        + "JOIN noteUser nu ON nu.noteNo = n.noteNo "
	        + "JOIN noteCategory nc ON nc.noteNo = n.noteNo "
	        + "JOIN category c ON c.categoryNo = nc.categoryNo "
	        + "LEFT JOIN favoriteNote fn ON fn.noteNo = n.noteNo "
	        + "LEFT JOIN favorite f ON fn.favoriteNo = f.favoriteNo "
	        + "LEFT JOIN noteReply nr ON nr.noteNo = n.noteNo "
	        + "LEFT JOIN reply r ON r.replyNo = nr.replyNo "
	        + "JOIN noteView nv ON nv.noteNo = n.noteNo "
	        + "JOIN view v ON v.viewNo = nv.viewNo "
	        + "WHERE nu.userNo = #{userNo} "
	        + "GROUP BY c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo "
	        + "ORDER BY n.createdAt DESC ")
	List<Map<String,Object>> getNoteListByUserNo(@Param("userNo") Integer userNo);

	@Insert(""
			+ "INSERT INTO noteUser "
			+ "(noteNo, userNo) "
			+ "VALUES "
			+ "(#{note.noteNo}, #{user.userNo})")
	void insert(NoteUser noteUser);
	
	@Select(""
			+ "SELECT u.* "
			+ "FROM user u JOIN noteUser nu on u.userNo = nu.userNo "
			+ "WHERE nu.noteNo = #{noteNo}" )
	User getUserByNoteNo(Integer noteNo); 
	
    @SelectProvider(type = SqlBuilder.class, method = "buildGetNoteList")
    List<Map<String, Object>> getNoteList(
            @Param("userNo") Integer userNo,
            @Param("sort") String sort,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("searchType") Integer searchType,
            @Param("searchInput") String searchInput);
    
    class SqlBuilder {
        public String buildGetNoteList(Map<String, Object> params) {
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
            sql.append("JOIN noteUser nu ON nu.noteNo = n.noteNo ");
            sql.append("JOIN noteCategory nc ON nc.noteNo = n.noteNo ");
            sql.append("JOIN category c ON c.categoryNo = nc.categoryNo ");
            sql.append("LEFT OUTER JOIN favoriteNote fn ON fn.noteNo = n.noteNo ");
            sql.append("LEFT OUTER JOIN favorite f ON fn.favoriteNo = f.favoriteNo ");
            sql.append("LEFT OUTER JOIN noteReply nr ON nr.noteNo = n.noteNo ");
            sql.append("LEFT OUTER JOIN reply r ON r.replyNo = nr.replyNo ");
            sql.append("JOIN noteView nv ON nv.noteNo = n.noteNo ");
            sql.append("JOIN view v ON v.viewNo = nv.viewNo ");
            sql.append("WHERE nu.userNo = #{userNo} ");

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

        private String getOrderByClause(String orderBy) {
            switch (orderBy) {
                case "favorite":
                    return "favorite_count DESC";
                case "reply":
                    return "reply_count DESC";
                default:
                    return "noteCreatedAt DESC";
            }
        }
    }

}
