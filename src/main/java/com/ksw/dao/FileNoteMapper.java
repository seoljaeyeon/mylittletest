package com.ksw.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileNoteMapper {

	@Insert(""
			+ "INSERT INTO fileNote "
			+ "(fileNo, noteNo) "
			+ "VALUES "
			+ "(#{fileNo}, #{noteNo})")
	void insert(@Param("fileNo") Integer fileNo, @Param("noteNo") Integer noteNo);
	
}
