package com.ksw.dao.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NoteUserMapper {

	@Insert(""
			+ "INSERT INTO noteUser "
			+ "(noteNo, userNo) "
			+ "VALUES "
			+ "(#{noteNo}, #{userNo})")
	void insert(@Param("noteNo") Integer noteNo, @Param("userNo") Integer userNo);
}
