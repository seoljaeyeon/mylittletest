package com.ksw.dao.relation;

import com.ksw.object.entity.mybatis.FavoriteNote;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteNoteMapper {

    @Insert("INSERT INTO favoriteNote (userNo, noteNo, favoriteNo) VALUES (#{userNo}, #{noteNo}, #{favoriteNo})")
    void insert(FavoriteNote favoriteNote);

    @Select("SELECT * FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    FavoriteNote findByUserNoAndNoteNo(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);

    @Select("SELECT noteNo FROM favoriteNote WHERE userNo = #{userNo} AND favoriteNo IN (SELECT favoriteNo FROM favorite WHERE favoriteType = -1)")
    List<Integer> findDislikedNoteNosByUserNo(@Param("userNo") Integer userNo);

    @Delete("DELETE FROM favoriteNote WHERE userNo = #{userNo} AND noteNo = #{noteNo}")
    void delete(@Param("userNo") Integer userNo, @Param("noteNo") Integer noteNo);
}
