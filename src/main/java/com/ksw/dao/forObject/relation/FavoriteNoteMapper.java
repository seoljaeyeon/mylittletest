package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.relation.FavoriteNote;

@Mapper
public interface FavoriteNoteMapper {
	
    @Select("SELECT COUNT(*) FROM favorite fv JOIN favoriteNote fvn "
    		+ "ON fv.favoriteNo = fvn.favoriteNo "
    		+ "WHERE fvn.noteNo = #{noteNo} AND fv.favoriteType = 1 ")
	Integer countFavoriteByNoteNo(@Param("noteNo") Integer noteNo);
	
	@Select("SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN favoriteNote fn ON nc.noteNo = fn.noteNo " +
	        "JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "JOIN noteView nv ON nv.noteNo = nc.noteNo " +
	        "WHERE (f.favoriteType = #{favoriteType} OR f.favoriteType = 1) AND fn.userNo = #{userNo} " +
	        "GROUP BY c.categoryNo " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getCategoryListByUserNoAndFavoriteType(@Param("userNo") Integer userNo, @Param("favoriteType") Integer favoriteType);
	
	@Select("SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt " +
	        "FROM category c " +
	        "JOIN noteCategory nc ON c.categoryNo = nc.categoryNo " +
	        "JOIN favoriteNote fn ON nc.noteNo = fn.noteNo " +
	        "JOIN favorite f ON fn.favoriteNo = f.favoriteNo " +
	        "JOIN noteView nv ON nv.noteNo = nc.noteNo " +
	        "WHERE f.favoriteType = #{favoriteType} AND fn.userNo = #{userNo} "
	        + "AND c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') " +
	        "GROUP BY c.categoryNo " +
	        "ORDER BY createdAt DESC")
	List<Map<String, Object>> getSimilarCategoryListByUserNoAndFavoriteType(
			@Param("userNo") Integer userNo, 
			@Param("favoriteType") Integer favoriteType,
			@Param("searchInput") String searchInput);

	@Select("SELECT " +
	        "f.favoriteType, " +
	        "c.categoryTitle, " +
	        "n.noteTitle AS bookmarkNote, " +
	        "f.createdAt, " +
	        "n.noteNo " +
	        "FROM favorite f " +
	        "LEFT JOIN favoriteNote fn ON f.favoriteNo = fn.favoriteNo AND fn.userNo = #{userNo} " +
	        "LEFT JOIN note n ON fn.noteNo = n.noteNo " +
	        "LEFT JOIN noteCategory nc ON n.noteNo = nc.noteNo " +
	        "LEFT JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "LEFT JOIN favoriteCategory fc ON f.favoriteNo = fc.favoriteNo AND fc.userNo = #{userNo} " +
	        "LEFT JOIN category c2 ON fc.categoryNo = c2.categoryNo " +
	        "WHERE (fn.userNo = #{userNo} OR fc.userNo = #{userNo}) AND (f.favoriteType = 1 OR f.favoriteType = 2) " +
	        "ORDER BY f.createdAt DESC " +
	        "LIMIT #{limit} OFFSET #{offset}")
	List<Map<String, Object>> getFavoriteListByUserNo(
	        @Param("userNo") Integer userNo,
	        @Param("limit") Integer limit,
	        @Param("offset") Integer offset);
	
	@Select("SELECT " +
	        "f.favoriteType, " +
	        "c.categoryTitle, " +
	        "n.noteTitle AS bookmarkNote, " +
	        "f.createdAt, " +
	        "n.noteNo " +
	        "FROM favorite f " +
	        "LEFT JOIN favoriteNote fn ON f.favoriteNo = fn.favoriteNo AND fn.userNo = #{userNo} " +
	        "LEFT JOIN note n ON fn.noteNo = n.noteNo " +
	        "LEFT JOIN noteCategory nc ON n.noteNo = nc.noteNo " +
	        "LEFT JOIN category c ON nc.categoryNo = c.categoryNo " +
	        "LEFT JOIN favoriteCategory fc ON f.favoriteNo = fc.favoriteNo AND fc.userNo = #{userNo} " +
	        "LEFT JOIN category c2 ON fc.categoryNo = c2.categoryNo " +
	        "WHERE (fn.userNo = #{userNo} OR fc.userNo = #{userNo}) AND (f.favoriteType = 1 OR f.favoriteType = 2) " +
	        "AND (c.categoryTitle LIKE CONCAT('%', #{searchInput}, '%') OR n.noteTitle LIKE CONCAT('%', #{searchInput}, '%')) " +
	        "ORDER BY f.createdAt DESC " +
	        "LIMIT #{limit} OFFSET #{offset}")
	List<Map<String, Object>> getFavoriteSimilarListByUserNo(
	        @Param("userNo") Integer userNo,
	        @Param("limit") Integer limit,
	        @Param("offset") Integer offset,
	        @Param("searchInput") String searchInput);
	
    @SelectProvider(type = SqlBuilder.class, method = "buildGetFavoritedNoteListByUserNo")
    List<Map<String, Object>> getFavoritedNoteListByUserNo(
            @Param("userNo") Integer userNo,
            @Param("sort") String sort,
            @Param("limit") Integer limit,
            @Param("offset") Integer offset,
            @Param("searchType") Integer searchType,
            @Param("searchInput") String searchInput);
    class SqlBuilder {
        public String buildGetFavoritedNoteListByUserNo(Map<String, Object> params) {
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
            sql.append("WHERE nv.userNo = #{userNo} AND f.favoriteType = 1 ");

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
			+ "SELECT f.favoriteNo "
			+ "FROM favorite f JOIN favoriteNote fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.noteNo = #{noteNo} AND fu.userNo = #{userNo} "
			+ "AND TIMESTAMPDIFF(SECOND, f.createdAt, NOW()) <= 2"
			+ "")
	Integer checkRecentFavoriteRequest(@Param("noteNo")Integer noteNo, @Param("userNo")Integer userNo);
	
	@Select(""
			+ "SELECT f.favoriteNo "
			+ "FROM favorite f JOIN favoriteNote fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.noteNo = #{noteNo} AND fu.userNo = #{userNo} "
			+ "")
	Integer getFavoriteNo(@Param("noteNo")Integer noteNo, @Param("userNo")Integer userNo);

    @Insert("INSERT INTO favoriteNote "
    		+ "(userNo, noteNo, favoriteNo) "
    		+ "VALUES "
    		+ "(#{userNo}, #{noteNo}, #{favoriteNo})")
    void insert(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo, @Param("favoriteNo") Integer favoriteNo);
    
    @Update("UPDATE favoriteNote "
    		+ "SET favoriteNo = #{favoriteNo} WHERE noteNo = #{noteNo} AND userNo = #{userNo} ")
    void update(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo, @Param("favoriteNo") Integer favoriteNo);

    @Select("SELECT * FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    FavoriteNote findByUserNoAndNoteNo(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

    @Select("SELECT noteNo FROM favoriteNote WHERE userNo = #{userNo} AND favoriteNo IN (SELECT favoriteNo FROM favorite WHERE favoriteType = -1)")
    List<Integer> findDislikedNoteNosByUserNo(@Param("userNo") Integer userNo);

    @Delete("DELETE FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    void delete(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

}
