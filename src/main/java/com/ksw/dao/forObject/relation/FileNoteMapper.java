package com.ksw.dao.forObject.relation;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.ksw.object.relation.FileNote;

@Mapper
public interface FileNoteMapper {

    @Insert(""
            + "INSERT INTO fileNote "
            + "(fileNo, noteNo) "
            + "VALUES "
            + "(#{file.fileNo}, #{note.noteNo})")
    void insert(FileNote fileNote);
	
}
