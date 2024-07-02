package com.ksw.dao.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoteCategoryMapper {

	@Insert(""
			+ "INSERT INTO noteCategory "
			+ "(categoryNo, userNo) "
			+ "VALUES "
			+ "(#{categoryNo}, #{userNo})")
	void insert(@Param("categoryNo") Integer categoryNo, @Param("userNo") Integer userNo);
	
}
