package com.ksw.dao.forObject.relation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.object.relation.FavoriteNote;

@Mapper
public interface FavoriteNoteMapper {
	
	@Select("WITH temp_table AS ("
			+ "	SELECT c.categoryNo, MAX(nv.createdAt) AS createdAt FROM category c "
			+ "	JOIN noteCategory nc ON c.categoryNo = nc.categoryNo "
			+ "	JOIN favoriteNote fn ON nc.noteNo = fn.noteNo"
			+ " JOIN favorite f ON fn.favoriteNo = f.favoriteNo "
			+ " WHERE f.favoriteType = 1 ) "
			+ "SELECT categoryNo "
			+ "FROM temp_table "
			+ "ORDER BY createdAt DESC")
	List<Integer> getCategoryListByUserNoAndFavoriteType(@Param("userNo") Integer userNo, @Param("favoriteType") Integer favoriteType);

	@Select("SELECT " +
	        "CASE WHEN f.favoriteType = 1 THEN '즐겨찾기' ELSE '북마크' END AS favoriteType, " +
	        "c.categoryTitle, " +
	        "n.noteTitle AS bookmarkNote, " +
	        "f.createdAt, " +
	        "n.noteNo " +
	        "FROM favorite f " +
	        "LEFT JOIN favoriteCategory fc ON fc.favoriteNo = f.favoriteNo " +
	        "LEFT JOIN category c ON fc.categoryNo = c.categoryNo " +
	        "LEFT JOIN noteCategory nc ON nc.categoryNo = c.categoryNo " +
	        "LEFT JOIN note n ON n.noteNo = nc.noteNo " +
	        "LEFT JOIN favoriteNote fn ON f.favoriteNo = fn.favoriteNo " +
	        "WHERE fn.userNo = #{userNo} OR fc.userNo = #{userNo} " +
	        "ORDER BY f.createdAt DESC " +
	        "LIMIT #{limit} OFFSET #{offset}")
	List<Map<String, Object>> getFavoriteListByUserNo(
	        @Param("userNo") Integer userNo,
	        @Param("limit") Integer limit,
	        @Param("offset") Integer offset);

	
	@Select("SELECT c.categoryTitle, n.noteTitle, n.createdAt, n.noteNo "
	        + "COUNT(CASE WHEN fn.favoriteType = 2 THEN 1 ELSE NULL END) AS favorite_count, "
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
	        + "GROUP BY c.categoryTitle, n.noteTitle, n.createdAt "
	        + "ORDER BY n.createdAt DESC")
	List<Map<String,Object>> getNoteListByUserNo(@Param("userNo") Integer userNo);
	
	@Select(""
			+ "SELECT f.*"
			+ "FROM favorite f JOIN favoriteNote fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.noteNo = #{noteNo} AND fu.userNo = #{userNo} "
			+ "AND TIMESTAMPDIFF(SECOND, f.createdAt, NOW()) <= 5"
			+ "")
	FavoriteDTO checkRecentFavoriteRequest(@Param("noteNo")Integer noteNo, @Param("userNo")Integer userNo);

    @Insert("INSERT INTO favoriteNote (userNo, noteNo, favoriteNo) VALUES (#{userNo}, #{noteNo}, #{favoriteNo})")
    void insert(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo, @Param("favoriteNo") Integer favoriteNo);

    @Select("SELECT * FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    FavoriteNote findByUserNoAndNoteNo(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

    @Select("SELECT noteNo FROM favoriteNote WHERE userNo = #{userNo} AND favoriteNo IN (SELECT favoriteNo FROM favorite WHERE favoriteType = -1)")
    List<Integer> findDislikedNoteNosByUserNo(@Param("userNo") Integer userNo);

    @Delete("DELETE FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    void delete(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

}
