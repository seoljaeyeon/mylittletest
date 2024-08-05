package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FavoriteCategoryMapper {

	@Select(""
			+ "SELECT f.favoriteNo"
			+ "FROM favorite f JOIN favoriteCategory fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.categoryNo = #{categoryNo} AND fu.userNo = #{userNo} "
			+ "AND TIMESTAMPDIFF(SECOND, f.createdAt, NOW()) <= 5"
			+ "")
	Integer checkRecentFavoriteRequest(@Param("categoryNo")Integer categoryNo, @Param("userNo")Integer userNo);
	
	@Select(""
			+ "SELECT f.favoriteNo"
			+ "FROM favorite f JOIN favoriteCategory fu ON f.favoriteNo = fu.favoriteNo "
			+ "WHERE fu.categoryNo = #{categoryNo} AND fu.userNo = #{userNo} ")
	Integer getFavorite(@Param("categoryNo")Integer categoryNo, @Param("userNo")Integer userNo);

    @Insert("INSERT INTO favoriteCategory (userNo, categoryNo, favoriteNo) VALUES (#{userNo}, #{categoryNo}, #{favoriteNo})")
    void insert(@Param("categoryNo") Integer categoryNo, @Param("userNo") Integer userNo, @Param("favoriteNo") Integer favoriteNo);
    
    @Update("UPDATE favoriteCategory SET favoriteNo = #{favoriteNo} WHERE userNo = #{userNo} AND categoryNo = #{categoryNo} ")
    void update(@Param("categoryNo") Integer categoryNo, @Param("userNo") Integer userNo, @Param("favoriteNo") Integer favoriteNo);
	
}
