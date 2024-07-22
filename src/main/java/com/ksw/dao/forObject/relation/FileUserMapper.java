package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.File;

@Mapper
public interface FileUserMapper {

	@Insert("INSERT INTO fileUser (fileNo, userNo) VALUES (#{fileNo}, #{userNo}) ")
	Integer insert(@Param("fileNo") Integer fileNo, @Param("userNo") Integer userNo);
	
	@Select("SELECT f.* " +
	        "FROM file f " +
	        "JOIN fileUser fu ON f.fileNo = fu.fileNo " +
	        "WHERE fu.userNo = #{userNo} " +
	        "ORDER BY f.createdAt DESC " +
	        "LIMIT 1")
	File getUserProfileFile(@Param("userNo") Integer userNo);
}
