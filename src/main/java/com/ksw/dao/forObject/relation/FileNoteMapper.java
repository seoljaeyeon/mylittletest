package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ksw.object.entity.File;
import com.ksw.object.relation.FileNote;

@Mapper
public interface FileNoteMapper {

    @Insert(""
            + "INSERT INTO fileNote "
            + "(fileNo, noteNo) "
            + "VALUES "
            + "(#{file.fileNo}, #{note.noteNo})")
    void insert(FileNote fileNote);
    
    @Select(""
    		+ "SELECT f.* FROM file f JOIN fileNote n "
    		+ "ON f.fileNo = n.fileNo "
    		+ "WHERE n.noteNo = #{noteNo}")
    File getFileByNoteNo(Integer noteNo);
	
}
