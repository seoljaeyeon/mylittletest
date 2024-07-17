package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.relation.FavoriteNote;

@Mapper
public interface FavoriteNoteMapper {

	@Select(""
			+ "SELECT f.favoriteNo"
			+ "FROM favorite f JOIN favoriteNote fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.noteNo = #{noteNo} AND fu.userNo = #{userNo} "
			+ "AND TIMESTAMPDIFF(SECOND, f.createdAt, NOW()) <= 5"
			+ "")
	Integer checkRecentFavoriteRequest(@Param("noteNo")Integer noteNo, @Param("userNo")Integer userNo);

    @Insert("INSERT INTO favoriteNote (userNo, noteNo, favoriteNo) VALUES (#{userNo}, #{noteNo}, #{favoriteNo})")
    void insert(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo, @Param("favoriteNo") Integer favoriteNo);

    @Select("SELECT * FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    FavoriteNote findByUserNoAndNoteNo(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

    @Select("SELECT noteNo FROM favoriteNote WHERE userNo = #{userNo} AND favoriteNo IN (SELECT favoriteNo FROM favorite WHERE favoriteType = -1)")
    List<Integer> findDislikedNoteNosByUserNo(@Param("userNo") Integer userNo);

    @Delete("DELETE FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    void delete(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

}
