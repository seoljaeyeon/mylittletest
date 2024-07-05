package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteCategoryMapper {

	@Insert(""
			+ "INSERT INTO noteCategory "
			+ "(categoryNo, userNo) "
			+ "VALUES "
			+ "(#{categoryNo}, #{userNo})")
	void insert(@Param("categoryNo") Integer categoryNo, @Param("userNo") Integer userNo);
	
    @Select("SELECT noteNo FROM noteCategory "
    		+ "WHERE categoryNo = #{categoryNo}")
    List<Integer> findNoteNosByCategoryNo(int categoryNo);
	
}
