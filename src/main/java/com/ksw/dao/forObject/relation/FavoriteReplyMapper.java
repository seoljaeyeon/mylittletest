package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ksw.object.relation.FavoriteReply;

@Mapper
public interface FavoriteReplyMapper {

	@Update("UPDATE favoriteReply SET favoriteNo = #{favoriteNo} WHERE userNo = #{userNo} AND replyNo = #{replyNo} ")
	void update(@Param("replyNo") Integer replyNo, @Param("userNo") Integer userNo, @Param("favoriteNo") Integer favoriteNo);

	@Select(""
	        + "SELECT f.favoriteNo "
	        + "FROM favorite f JOIN favoriteReply fu ON f.favoriteNo = fu.favoriteNo "
	        + "WHERE fu.replyNo = #{replyNo} AND fu.userNo = #{userNo} "
	        + "AND TIMESTAMPDIFF(SECOND, f.createdAt, NOW()) <= 5")
	Integer checkRecentFavoriteRequest(@Param("replyNo") Integer replyNo, @Param("userNo") Integer userNo);

	@Select(""
	        + "SELECT f.favoriteNo "
	        + "FROM favorite f JOIN favoriteReply fu ON f.favoriteNo = fu.favoriteNo "
	        + "WHERE fu.replyNo = #{replyNo} AND fu.userNo = #{userNo} ")
	Integer getFavoriteNo(@Param("replyNo") Integer replyNo, @Param("userNo") Integer userNo);
	

    @Insert("INSERT INTO favoriteReply (userNo, replyNo, favoriteNo) VALUES (#{userNo}, #{replyNo}, #{favoriteNo})")
    void insert(@Param("replyNo") Integer replyNo, @Param("userNo") Integer userNo,  @Param("favoriteNo") Integer favoriteNo);

    @Select("SELECT * FROM favoriteReply WHERE userNo = #{userNo} AND replyNo = #{replyNo}")
    FavoriteReply findByUserNoAndReplyNo(@Param("userNo") Integer userNo, @Param("replyNo") Integer replyNo);

    @Select("SELECT replyNo FROM favoriteReply WHERE userNo = #{userNo} AND favoriteNo IN (SELECT favoriteNo FROM favorite WHERE favoriteType = -1)")
    List<Integer> findDislikedReplyNosByUserNo(@Param("userNo") Integer userNo);

    @Delete("DELETE FROM favoriteReply WHERE userNo = #{userNo} AND replyNo = #{replyNo}")
    void delete(@Param("userNo") Integer userNo, @Param("replyNo") Integer replyNo);

}
