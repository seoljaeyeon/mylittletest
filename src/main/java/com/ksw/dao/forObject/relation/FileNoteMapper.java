package com.ksw.dao.forObject.relation;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.File;

@Mapper
public interface FileNoteMapper {

    @Insert(""
            + "INSERT INTO fileNote "
            + "(fileNo, noteNo) "
            + "VALUES "
            + "(#{fileNo}, #{userNo})")
    void insert(@Param("fileNo") Integer fileNo, @Param("userNo") Integer userNo);
    
    @Select(""
    		+ "SELECT f.* FROM file f JOIN fileNote n "
    		+ "ON f.fileNo = n.fileNo "
    		+ "WHERE n.noteNo = #{noteNo} ")
    List<File> getFileByNoteNo(Integer noteNo);
	
}
